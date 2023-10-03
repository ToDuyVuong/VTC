package hcmute.tlcn.vtc.controller.user;

import hcmute.tlcn.vtc.authentication.service.IJwtService;
import hcmute.tlcn.vtc.dto.CustomerDTO;
import hcmute.tlcn.vtc.dto.user.response.ProfileCustomerResponse;
import hcmute.tlcn.vtc.entity.Customer;
import hcmute.tlcn.vtc.repository.CustomerRepository;
import hcmute.tlcn.vtc.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private final ICustomerService customerService;
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final ModelMapper modelMapper;
//    @Autowired
//    private final IJwtService jwtService;


//    @GetMapping("/profile")
//    public ResponseEntity<ProfileCustomerResponse> getProfileCustomer(@RequestHeader("Authorization") String token) {
//
//        System.out.println("token: " + token);
//
//
//
//        ProfileCustomerResponse profileCustomerResponse = customerService.getProfileCustomer(token);
//        return ResponseEntity.ok(profileCustomerResponse);
//    }

    @GetMapping("/profile")
        public ResponseEntity<ProfileCustomerResponse> getProfileCustomer(Authentication authentication) {

        System.out.println("token: " );

//        Customer customer = customerRepository.findByUsername("user1")
//                .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại."));
//
//        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);

        ProfileCustomerResponse profileCustomerResponse = new ProfileCustomerResponse();
//        profileCustomerResponse.setCustomerDTO(customerDTO);
//        profileCustomerResponse.setMessage("Lấy thông tin khách hàng thành công.");
//        profileCustomerResponse.setStatus("ok");
//        profileCustomerResponse.setCode(200);



        return ResponseEntity.ok(profileCustomerResponse);
    }

}