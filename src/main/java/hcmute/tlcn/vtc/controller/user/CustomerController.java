package hcmute.tlcn.vtc.controller.user;

import hcmute.tlcn.vtc.authentication.service.IJwtService;
import hcmute.tlcn.vtc.dto.CustomerDTO;
import hcmute.tlcn.vtc.dto.user.request.ChangePasswordRequest;
import hcmute.tlcn.vtc.dto.user.request.ProfileCustomerRequest;
import hcmute.tlcn.vtc.dto.user.response.ProfileCustomerResponse;
import hcmute.tlcn.vtc.dto.user.response.SendEmailForgotPasswordResponse;
import hcmute.tlcn.vtc.entity.Customer;
import hcmute.tlcn.vtc.repository.CustomerRepository;
import hcmute.tlcn.vtc.service.ICustomerService;
import hcmute.tlcn.vtc.service.IMailService;
import hcmute.tlcn.vtc.service.IOtpService;
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
public class CustomerController {

    @Autowired
    private final ICustomerService customerService;
    @Autowired
    private final IMailService mailService;
    @Autowired
    private final IOtpService otpService;


    @GetMapping("/profile")
    public ResponseEntity<ProfileCustomerResponse> getProfileCustomer(@RequestBody String username) {
        ProfileCustomerResponse profileCustomerResponse = customerService.getProfileCustomer(username);
        return ResponseEntity.ok(profileCustomerResponse);
    }


    @PutMapping("/profile")
    public ResponseEntity<ProfileCustomerResponse> updateProfileCustomer(@RequestBody ProfileCustomerRequest request) {
        ProfileCustomerResponse profileCustomerResponse = customerService.updateProfileCustomer(request);
        return ResponseEntity.ok(profileCustomerResponse);
    }


    @PatchMapping("/change-password")
    public ResponseEntity<ProfileCustomerResponse> changePassword(@RequestBody ChangePasswordRequest request) {
        ProfileCustomerResponse profileCustomerResponse = customerService.changePassword(request);
        return ResponseEntity.ok(profileCustomerResponse);
    }

    @GetMapping("/forgot-password")
    public ResponseEntity<SendEmailForgotPasswordResponse> sendMailForgotPassword(@RequestParam("username") String username) {
        SendEmailForgotPasswordResponse response = mailService.sendMailForgotPassword(username);
        return ResponseEntity.ok(response);
    }

//    @PatchMapping("/reset-password")
//    public ResponseEntity<ProfileCustomerResponse> resetPassword(@RequestParam("newPassword") String newPassword,
//                                                                 @RequestParam("username") String username) {
//        ProfileCustomerResponse profileCustomerResponse = customerService.resetPassword(request);
//        return ResponseEntity.ok(profileCustomerResponse);
//    }



}