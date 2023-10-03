package hcmute.tlcn.vtc.service.impl;

import hcmute.tlcn.vtc.authentication.service.IJwtService;
import hcmute.tlcn.vtc.dto.CustomerDTO;
import hcmute.tlcn.vtc.dto.user.response.ProfileCustomerResponse;
import hcmute.tlcn.vtc.entity.Customer;
import hcmute.tlcn.vtc.repository.CustomerRepository;
import hcmute.tlcn.vtc.service.ICustomerService;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ProfileCustomerResponse getProfileCustomer(String token) {

        String username;
        try {
            username = jwtService.extractUsername(token);
        } catch (NotFoundException e) {
            throw new NotFoundException("Không tìm thấy thông tin tài khoản từ token.");
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


}
