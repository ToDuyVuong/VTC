package hcmute.tlcn.vtc.service.impl;

import hcmute.tlcn.vtc.authentication.service.IJwtService;
import hcmute.tlcn.vtc.dto.CustomerDTO;
import hcmute.tlcn.vtc.dto.user.request.ChangePasswordRequest;
import hcmute.tlcn.vtc.dto.user.request.ProfileCustomerRequest;
import hcmute.tlcn.vtc.dto.user.response.ProfileCustomerResponse;
import hcmute.tlcn.vtc.entity.Customer;
import hcmute.tlcn.vtc.repository.CustomerRepository;
import hcmute.tlcn.vtc.service.ICustomerService;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Customer getCustomerByUsername(String username) {
        return customerRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Tài khoản không tồn tại."));
    }


    @Override
    public ProfileCustomerResponse getProfileCustomer(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Tài khoản không được để trống.");
        }
        Customer customer = getCustomerByUsername(username);
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);

        ProfileCustomerResponse response = new ProfileCustomerResponse();
        response.setCustomerDTO(customerDTO);
        response.setMessage("Lấy thông tin khách hàng thành công.");
        response.setStatus("ok");
        response.setCode(200);

        return response;
    }


    @Override
    public ProfileCustomerResponse updateProfileCustomer(ProfileCustomerRequest request) {
        request.validate();

        Customer customerUpdate = getCustomerByUsername(request.getUsername());
//        if (customerUpdate == null) {
//            throw new NotFoundException("Tài khoản không tồn tại.");
//        }

        customerUpdate.setBirthday(request.getBirthday());
        customerUpdate.setEmail(request.getEmail());
        customerUpdate.setFullName(request.getFullName());
        customerUpdate.setGender(request.isGender());
        customerUpdate.setAtUpdate(LocalDateTime.now());
        customerRepository.save(customerUpdate);

        CustomerDTO customerDTO = modelMapper.map(customerUpdate, CustomerDTO.class);
        ProfileCustomerResponse response = new ProfileCustomerResponse();
        response.setCustomerDTO(customerDTO);
        response.setMessage("Cập nhật thông tin khách hàng thành công.");
        response.setStatus("ok");
        response.setCode(200);

        return response;

    }


    @Override
    public ProfileCustomerResponse changePassword(ChangePasswordRequest request) {
        request.validate();
        Customer customerUpdate = getCustomerByUsername(request.getUsername());

        boolean checkPassword = passwordEncoder.matches(request.getOldPassword(), customerUpdate.getPassword());
        if (!checkPassword) {
            throw new IllegalArgumentException("Mật khẩu cũ không chính xác.");
        }

        customerUpdate.setPassword(passwordEncoder.encode(request.getNewPassword()));
        customerUpdate.setAtUpdate(LocalDateTime.now());
        customerRepository.save(customerUpdate);

        CustomerDTO customerDTO = modelMapper.map(customerUpdate, CustomerDTO.class);
        ProfileCustomerResponse response = new ProfileCustomerResponse();
        response.setCustomerDTO(customerDTO);
        response.setMessage("Cập nhật mật khẩu của khách hàng thành công.");
        response.setStatus("ok");
        response.setCode(200);

        return response;
    }

}
