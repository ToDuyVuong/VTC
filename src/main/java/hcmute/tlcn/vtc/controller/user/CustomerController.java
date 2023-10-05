package hcmute.tlcn.vtc.controller.user;

import hcmute.tlcn.vtc.authentication.service.IJwtService;
import hcmute.tlcn.vtc.dto.CustomerDTO;
import hcmute.tlcn.vtc.dto.user.request.ChangePasswordRequest;
import hcmute.tlcn.vtc.dto.user.request.ProfileCustomerRequest;
import hcmute.tlcn.vtc.dto.user.response.ProfileCustomerResponse;
import hcmute.tlcn.vtc.entity.Customer;
import hcmute.tlcn.vtc.repository.CustomerRepository;
import hcmute.tlcn.vtc.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
//@PreAuthorize("hasRole('CUSTOMER')")

public class CustomerController {

    @Autowired
    private final ICustomerService customerService;
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final ModelMapper modelMapper;

    //    @PreAuthorize("hasAuthority('customer:read')")
    @GetMapping("/profile")
    public ResponseEntity<ProfileCustomerResponse> getProfileCustomer(@RequestBody String username) {
        ProfileCustomerResponse profileCustomerResponse = customerService.getProfileCustomer(username);
        return ResponseEntity.ok(profileCustomerResponse);
    }


    @PutMapping("/profile")
    public ResponseEntity<ProfileCustomerResponse> updateProfileCustomer02(@RequestBody ProfileCustomerRequest profileCustomerRequest) {

        System.out.println("customerDTO 202: " + profileCustomerRequest);
        ProfileCustomerResponse profileCustomerResponse = customerService.updateProfileCustomer(profileCustomerRequest);

        return ResponseEntity.ok(profileCustomerResponse);
    }



}