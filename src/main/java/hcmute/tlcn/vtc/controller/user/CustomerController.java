package hcmute.tlcn.vtc.controller.user;

import hcmute.tlcn.vtc.dto.user.request.LoginRequest;
import hcmute.tlcn.vtc.dto.user.request.RegisterRequest;
import hcmute.tlcn.vtc.dto.user.response.LoginResponse;
import hcmute.tlcn.vtc.dto.user.response.RegisterResponse;
import hcmute.tlcn.vtc.service.ICustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<RegisterResponse> registerCustomer(@RequestBody RegisterRequest customerRequest) {

        return ResponseEntity.ok(customerService.register(customerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authentication(@RequestBody LoginRequest loginRequest) {
        System.out.println("Login request: " + loginRequest);
        LoginResponse loginResponse = customerService.login(loginRequest);
        System.out.println("Login success response: " + loginResponse);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        customerService.refreshToken(request, response);
    }

}
