package hcmute.tlcn.vtc.controller.demo;

import hcmute.tlcn.vtc.model.dto.user.request.ProfileCustomerRequest;
import hcmute.tlcn.vtc.model.dto.user.response.ProfileCustomerResponse;
import hcmute.tlcn.vtc.service.user.ICustomerService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customer")
@RequiredArgsConstructor
@PreAuthorize("hasRole('CUSTOMER')")
@Hidden
public class V1Controller {

    @Autowired
    private final ICustomerService customerService;

    @PreAuthorize("hasAuthority('customer:read')")
    @GetMapping()
    public ResponseEntity<ProfileCustomerResponse> getProfileCustomer(@RequestBody String username) {
        ProfileCustomerResponse profileCustomerResponse = customerService.getProfileCustomer(username);
        return ResponseEntity.ok(profileCustomerResponse);
    }

    @PreAuthorize("hasAuthority('customer:update') ")
    @PutMapping("/update")
    public ResponseEntity<?> changePassword(@RequestBody ProfileCustomerRequest request) {
        System.out.println("customerDTO 2 : " + request);
        ProfileCustomerResponse profileCustomerResponse = customerService.updateProfileCustomer(request);
        return ResponseEntity.ok(profileCustomerResponse);
    }


}