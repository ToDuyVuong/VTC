package hcmute.tlcn.vtc.controller.user;

import hcmute.tlcn.vtc.dto.user.request.LoginRequest;
import hcmute.tlcn.vtc.dto.user.request.RegisterRequest;
import hcmute.tlcn.vtc.dto.user.response.LoginResponse;
import hcmute.tlcn.vtc.dto.user.response.RefreshTokenResponse;
import hcmute.tlcn.vtc.dto.user.response.RegisterResponse;
import hcmute.tlcn.vtc.service.ICustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<LoginResponse> authentication(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        LoginResponse loginResponse = customerService.login(loginRequest, response);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshToken(
            @CookieValue(name = "refreshToken") String refreshToken,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        return ResponseEntity.ok(customerService.refreshToken(refreshToken, request, response ));
    }

}
