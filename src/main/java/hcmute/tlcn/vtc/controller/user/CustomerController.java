package hcmute.tlcn.vtc.controller.user;

import hcmute.tlcn.vtc.dto.ResponseAbstract;
import hcmute.tlcn.vtc.dto.user.request.ChangePasswordRequest;
import hcmute.tlcn.vtc.dto.user.request.ForgotPasswordRequest;
import hcmute.tlcn.vtc.dto.user.request.ProfileCustomerRequest;
import hcmute.tlcn.vtc.dto.user.response.ProfileCustomerResponse;
import hcmute.tlcn.vtc.dto.user.response.ForgotPasswordResponse;
import hcmute.tlcn.vtc.service.ICustomerService;
import hcmute.tlcn.vtc.service.IMailService;
import hcmute.tlcn.vtc.service.IOtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ProfileCustomerResponse> getProfileCustomer(@RequestParam String username) {
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
    public ResponseEntity<ForgotPasswordResponse> sendMailForgotPassword(@RequestParam("username") String username) {
        ForgotPasswordResponse response = mailService.sendMailForgotPassword(username);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ForgotPasswordResponse> resetPassword(@RequestBody ForgotPasswordRequest request) {

        ForgotPasswordResponse response = customerService.resetPassword(request);


        return ResponseEntity.ok(response);
    }



}