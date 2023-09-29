package hcmute.tlcn.vtc.service.impl;

import hcmute.tlcn.vtc.configuration.security.JwtService;
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
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

        customerRepository.save(customer);
        RegisterSuccessResponse registerSuccessResponse = modelMapper.map(customer, RegisterSuccessResponse.class);
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
        if (!customer.isPresent()) {
            throw new NotFoundException("Tài khoản không tồn tại.");
        } else if (!customer.get().getPassword().equals(loginRequest.getPassword())) {
            throw new InvalidPasswordException("Sai mật khẩu");
        }

        LoginSuccessResponse loginSuccessResponse = modelMapper.map(customer, LoginSuccessResponse.class);
        loginSuccessResponse.setStatus("ok");
        loginSuccessResponse.setMessage("Đăng nhập thành công");
        loginSuccessResponse.setToken(jwtService.generateToken(customer.get()));

        return loginSuccessResponse;
    }

}
