package hcmute.tlcn.vtc.controller.user;

import hcmute.tlcn.vtc.model.dto.user.request.ChangePasswordRequest;
import hcmute.tlcn.vtc.model.dto.user.request.ForgotPasswordRequest;
import hcmute.tlcn.vtc.model.dto.user.request.ProfileCustomerRequest;
import hcmute.tlcn.vtc.model.dto.user.response.ProfileCustomerResponse;
import hcmute.tlcn.vtc.model.dto.user.response.ForgotPasswordResponse;
import hcmute.tlcn.vtc.service.user.ICustomerService;
import hcmute.tlcn.vtc.service.user.IMailService;
import jakarta.servlet.http.HttpServletRequest;
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


    @GetMapping("/profile")
    public ResponseEntity<ProfileCustomerResponse> getProfileCustomer(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        ProfileCustomerResponse profileCustomerResponse = customerService.getProfileCustomer(username);
        return ResponseEntity.ok(profileCustomerResponse);
    }


    @PutMapping("/profile")
    public ResponseEntity<ProfileCustomerResponse> updateProfileCustomer(
            @RequestBody ProfileCustomerRequest profileCustomerRequest,
            HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        profileCustomerRequest.setUsername(username);
        ProfileCustomerResponse profileCustomerResponse = customerService.updateProfileCustomer(profileCustomerRequest);
        return ResponseEntity.ok(profileCustomerResponse);
    }


    @PatchMapping("/change-password")
    public ResponseEntity<ProfileCustomerResponse> changePassword(
            @RequestBody ChangePasswordRequest changePasswordRequest,
            HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        changePasswordRequest.setUsername(username);
        ProfileCustomerResponse profileCustomerResponse = customerService.changePassword(changePasswordRequest);
        return ResponseEntity.ok(profileCustomerResponse);
    }


    @GetMapping("/forgot-password")
    public ResponseEntity<ForgotPasswordResponse> sendMailForgotPassword(@RequestParam("username") String username) {
        ForgotPasswordResponse response = mailService.sendMailForgotPassword(username);
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/reset-password")
    public ResponseEntity<ForgotPasswordResponse> resetPassword(@RequestBody ForgotPasswordRequest request) {
        ForgotPasswordResponse response = customerService.resetPassword(request);
        return ResponseEntity.ok(response);
    }



}