package hcmute.tlcn.vtc.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import hcmute.tlcn.vtc.entity.Token;
import hcmute.tlcn.vtc.entity.extra.TokenType;
import hcmute.tlcn.vtc.repository.TokenRepository;
import hcmute.tlcn.vtc.service.JwtService;
import hcmute.tlcn.vtc.dto.user.request.LoginRequest;
import hcmute.tlcn.vtc.dto.user.request.RegisterCustomerRequest;
import hcmute.tlcn.vtc.dto.user.response.LoginSuccessResponse;
import hcmute.tlcn.vtc.dto.user.response.RegisterSuccessResponse;
import hcmute.tlcn.vtc.entity.Customer;
import hcmute.tlcn.vtc.entity.extra.Role;
import hcmute.tlcn.vtc.repository.CustomerRepository;
import hcmute.tlcn.vtc.service.ICustomerService;
import hcmute.tlcn.vtc.util.exception.DuplicateEntryException;
import hcmute.tlcn.vtc.util.exception.InvalidPasswordException;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final TokenRepository tokenRepository;

    @Override
    public RegisterSuccessResponse registerCustomer(RegisterCustomerRequest customerRequest) {
        customerRequest.validate();

        Optional<Customer> existingCustomer = customerRepository.findByUsername(customerRequest.getUsername());
        if (existingCustomer.isPresent()) {
            throw new DuplicateEntryException("Tài khoản đã tồn tại.");
        }
        existingCustomer = customerRepository.findByEmail(customerRequest.getEmail());
        if (existingCustomer.isPresent()) {
            throw new DuplicateEntryException("Email đã được đăng ký.");
        }

        Customer customer = modelMapper.map(customerRequest, Customer.class);
        customer.setRole(Role.CUSTOMER);
        customer.setPassword(passwordEncoder.encode(customerRequest.getPassword()));



      Customer saveCustomer =   customerRepository.save(customer);

        var jwtToken = jwtService.generateToken(saveCustomer);
        var refreshToken = jwtService.generateRefreshToken(saveCustomer);

        RegisterSuccessResponse registerSuccessResponse = modelMapper.map(saveCustomer, RegisterSuccessResponse.class);
        registerSuccessResponse.setStatus("ok");
        registerSuccessResponse.setMessage("Đăng ký thành công");

        return registerSuccessResponse;
    }

    @Override
    public LoginSuccessResponse loginCustomer(LoginRequest loginRequest) {
        loginRequest.validate();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        Optional<Customer> customer = customerRepository.findByUsername(loginRequest.getUsername());

        System.out.println("Customer: " + customer);
        System.out.println("Customer password: " + customer.get().getPassword().equals(loginRequest.getPassword()));

        if (customer.isPresent()) {
            throw new NotFoundException("Tài khoản không tồn tại.");
        }

        String password = passwordEncoder.encode(loginRequest.getPassword());

        Customer existingCustomer = customer.get();



        if (!existingCustomer.getPassword().equals(password)) {
            throw new InvalidPasswordException("Sai mật khẩu");
        }


        var jwtToken = jwtService.generateToken(customer.get());
        var refreshToken = jwtService.generateRefreshToken(customer.get());
        revokeAllCustomerTokens(customer.get());
        saveCustomerToken(customer.get(), jwtToken);



        LoginSuccessResponse loginSuccessResponse = modelMapper.map(customer, LoginSuccessResponse.class);
        loginSuccessResponse.setStatus("ok");
        loginSuccessResponse.setMessage("Đăng nhập thành công");
        loginSuccessResponse.setAccessToken(jwtToken);
        loginSuccessResponse.setRefreshToken(refreshToken);


        return loginSuccessResponse;
    }




    @Override
    public void saveCustomerToken(Customer customer, String jwtToken) {
        var token = Token.builder()
                .customer(customer)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    @Override
    public void revokeAllCustomerTokens(Customer customer) {
        var validUserTokens = tokenRepository.findAllValidTokenByCustomer(customer.getCustomerId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    @Override
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            var user = this.customerRepository.findByUsername(username)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllCustomerTokens(user);
                saveCustomerToken(user, accessToken);
                var authResponse = LoginSuccessResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

}