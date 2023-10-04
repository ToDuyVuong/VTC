package hcmute.tlcn.vtc.authentication.service;

import hcmute.tlcn.vtc.authentication.response.LogoutResponse;
import hcmute.tlcn.vtc.authentication.response.RefreshTokenResponse;
import hcmute.tlcn.vtc.dto.CustomerDTO;
import hcmute.tlcn.vtc.entity.Token;
import hcmute.tlcn.vtc.entity.extra.TokenType;
import hcmute.tlcn.vtc.repository.TokenRepository;
import hcmute.tlcn.vtc.authentication.request.LoginRequest;
import hcmute.tlcn.vtc.authentication.request.RegisterRequest;
import hcmute.tlcn.vtc.authentication.response.LoginResponse;
import hcmute.tlcn.vtc.authentication.response.RegisterResponse;
import hcmute.tlcn.vtc.entity.Customer;
import hcmute.tlcn.vtc.entity.extra.Role;
import hcmute.tlcn.vtc.repository.CustomerRepository;
import hcmute.tlcn.vtc.util.exception.DuplicateEntryException;
import hcmute.tlcn.vtc.util.exception.JwtException;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import hcmute.tlcn.vtc.util.exception.TokenExpiredException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final IJwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final TokenRepository tokenRepository;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private int refreshExpiration;

    @Override
    public RegisterResponse register(RegisterRequest customerRequest) {
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
        customer.setAtCreate(OffsetDateTime.now());
        customer.setAtUpdate(OffsetDateTime.now());

        var saveCustomer = customerRepository.save(customer);
        RegisterResponse registerResponse = modelMapper.map(saveCustomer, RegisterResponse.class);
        registerResponse.setStatus("ok");
        registerResponse.setMessage("Đăng ký thành công");

        return registerResponse;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest, HttpServletResponse response) {
        loginRequest.validate();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        Optional<Customer> customer = customerRepository.findByUsername(loginRequest.getUsername());

        var jwtToken = jwtService.generateToken(customer.get());
        var refreshToken = jwtService.generateRefreshToken(customer.get());
        saveCustomerToken(customer.get(), refreshToken);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setCustomerDTO(modelMapper.map(customer, CustomerDTO.class));
        loginResponse.setStatus("ok");
        loginResponse.setMessage("Đăng nhập thành công");
        loginResponse.setCode(200);
        loginResponse.setAccessToken(jwtToken);
        loginResponse.setRefreshToken(refreshToken);

        // Lưu refreshToken vào cookie
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/"); // Đặt đúng path mà bạn muốn
        cookie.setMaxAge(refreshExpiration); // Set thời gian sống của cookie (ví dụ: 30 ngày)
        response.addCookie(cookie); // Thêm cookie vào response

        return loginResponse;
    }

    @Override
    public LogoutResponse logout(String refreshToken) {

        if (refreshToken == null) {
            throw new IllegalArgumentException("Token không được null. Đăng xuất thất bại.");
        }

        var storedToken = tokenRepository.findByToken(refreshToken);
        if (storedToken.isEmpty()) {
            throw new NotFoundException("Token không tồn tại. Đăng xuất thất bại.");
        }

        var token = storedToken.get();
        if (token.isExpired() || token.isRevoked()) {
            throw new TokenExpiredException("Token đã hết hạn. Đăng xuất thất bại.");
        }

        token.setExpired(true);
        token.setRevoked(true);
        tokenRepository.save(token);
        SecurityContextHolder.clearContext();

        return new LogoutResponse("success", "Đăng xuất thành công", 200);
    }



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
    public RefreshTokenResponse refreshToken(String refreshToken, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (refreshToken == null) {
            throw new JwtException("Refresh token không tồn tại.");
        }

        if (jwtService.isTokenExpired(refreshToken)) {
            throw new JwtException("Refresh token đã hết hạn.");
        }

        // Validate and extract username from refreshToken
        String username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            Optional<Customer> optionalCustomer = customerRepository.findByUsername(username);

            if (optionalCustomer.isPresent()) {
                Customer customer = optionalCustomer.get();

                if (jwtService.isTokenValid(refreshToken, customer)) {
                    String accessToken = jwtService.generateToken(customer);
                    revokeAllCustomerTokens(customer);
                    saveCustomerToken(customer, accessToken);

                    RefreshTokenResponse refreshTokenResponse = new RefreshTokenResponse();
                    refreshTokenResponse.setAccessToken(accessToken);
                    refreshTokenResponse.setStatus("ok");
                    refreshTokenResponse.setMessage("Refresh token thành công");
                    refreshTokenResponse.setCode(200);
                    return refreshTokenResponse;
                }
            } else {
                throw new NotFoundException("Tài khoản không tồn tại.");
            }
        } else {
            throw new JwtException("Lỗi xác thực token.");
        }
        return null;
    }


}