package hcmute.tlcn.vtc.service.impl;

import hcmute.tlcn.vtc.authentication.service.IJwtService;
import hcmute.tlcn.vtc.dto.CustomerDTO;
import hcmute.tlcn.vtc.dto.user.request.ProfileCustomerRequest;
import hcmute.tlcn.vtc.dto.user.response.ProfileCustomerResponse;
import hcmute.tlcn.vtc.entity.Customer;
import hcmute.tlcn.vtc.repository.CustomerRepository;
import hcmute.tlcn.vtc.service.ICustomerService;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private IJwtService jwtService;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Customer getCustomerByUsername(String username) {
        return customerRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Tài khoản không tồn tại."));
    }


    @Override
    public ProfileCustomerResponse getProfileCustomer(String username) {
        if(username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Tài khoản không được để trống.");
        }
        Customer customer = getCustomerByUsername(username);
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);

        ProfileCustomerResponse profileCustomerResponse = new ProfileCustomerResponse();
        profileCustomerResponse.setCustomerDTO(customerDTO);
        profileCustomerResponse.setMessage("Lấy thông tin khách hàng thành công.");
        profileCustomerResponse.setStatus("ok");
        profileCustomerResponse.setCode(200);

        return profileCustomerResponse;
    }


    @Override
    public ProfileCustomerResponse updateProfileCustomer(ProfileCustomerRequest profileCustomerRequest) {
        profileCustomerRequest.validate();

        Customer customerUpdate = getCustomerByUsername(profileCustomerRequest.getUsername());
        if (customerUpdate == null) {
            throw new NotFoundException("Tài khoản không tồn tại.");
        }

        customerUpdate.setBirthday(profileCustomerRequest.getBirthday());
        customerUpdate.setEmail(profileCustomerRequest.getEmail());
        customerUpdate.setFullName(profileCustomerRequest.getFullName());
        customerUpdate.setGender(profileCustomerRequest.isGender());
        customerUpdate.setAtUpdate(LocalDateTime.now());
        customerRepository.save(customerUpdate);

        CustomerDTO customerDTO = modelMapper.map(customerUpdate, CustomerDTO.class);
        ProfileCustomerResponse profileCustomerResponse = new ProfileCustomerResponse();
        profileCustomerResponse.setCustomerDTO(customerDTO);
        profileCustomerResponse.setMessage("Cập nhật thông tin khách hàng thành công.");
        profileCustomerResponse.setStatus("ok");
        profileCustomerResponse.setCode(200);

        return profileCustomerResponse;

    }

}
