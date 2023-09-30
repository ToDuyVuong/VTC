package hcmute.tlcn.vtc.controller.user;

import hcmute.tlcn.vtc.dto.user.request.LoginRequest;
import hcmute.tlcn.vtc.dto.user.request.RegisterCustomerRequest;
import hcmute.tlcn.vtc.dto.user.response.LoginSuccessResponse;
import hcmute.tlcn.vtc.dto.user.response.RegisterSuccessResponse;
import hcmute.tlcn.vtc.service.ICustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<RegisterSuccessResponse> registerCustomer(RegisterCustomerRequest customerRequest) {

        return ResponseEntity.ok(customerService.registerCustomer(customerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginSuccessResponse> loginCustomer(LoginRequest loginRequest) {
        System.out.println("Login request: " + loginRequest);
        LoginSuccessResponse loginSuccessResponse = customerService.loginCustomer(loginRequest);
        System.out.println("Login success response: " + loginSuccessResponse);
        return ResponseEntity.ok(loginSuccessResponse);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        customerService.refreshToken(request, response);
    }

}
