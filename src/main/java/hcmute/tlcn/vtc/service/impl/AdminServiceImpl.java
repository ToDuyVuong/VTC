package hcmute.tlcn.vtc.service.impl;

import hcmute.tlcn.vtc.authentication.request.RegisterRequest;
import hcmute.tlcn.vtc.authentication.response.RegisterResponse;
import hcmute.tlcn.vtc.entity.Customer;
import hcmute.tlcn.vtc.entity.extra.Role;
import hcmute.tlcn.vtc.repository.CustomerRepository;
import hcmute.tlcn.vtc.service.IAdminService;
import hcmute.tlcn.vtc.util.exception.DuplicateEntryException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse register(RegisterRequest adminRequest) {
        adminRequest.validate();

        Optional<Customer> existingCustomer = customerRepository.findByUsername(adminRequest.getUsername());
        if (existingCustomer.isPresent()) {
            throw new DuplicateEntryException("Tài khoản đã tồn tại.");
        }
        existingCustomer = customerRepository.findByEmail(adminRequest.getEmail());
        if (existingCustomer.isPresent()) {
            throw new DuplicateEntryException("Email đã được đăng ký.");
        }

        Customer customer = modelMapper.map(adminRequest, Customer.class);
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);  // Every customer has a CUSTOMER role
        customer.setRoles(roles);
        customer.setPassword(passwordEncoder.encode(adminRequest.getPassword()));
        customer.setAtCreate(LocalDateTime.now());
        customer.setAtUpdate(LocalDateTime.now());

        var saveCustomer = customerRepository.save(customer);
        RegisterResponse registerResponse = modelMapper.map(saveCustomer, RegisterResponse.class);
        registerResponse.setStatus("ok");
        registerResponse.setMessage("Đăng ký tài khoản admin thành công");

        return registerResponse;
    }
}
