package hcmute.tlcn.vtc.authentication;

import hcmute.tlcn.vtc.authentication.request.LoginRequest;
import hcmute.tlcn.vtc.authentication.request.RegisterRequest;
import hcmute.tlcn.vtc.authentication.response.LoginResponse;
import hcmute.tlcn.vtc.authentication.response.LogoutResponse;
import hcmute.tlcn.vtc.authentication.response.RefreshTokenResponse;
import hcmute.tlcn.vtc.authentication.response.RegisterResponse;
import hcmute.tlcn.vtc.authentication.service.IAuthenticationService;
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
public class AuthenticationController {

    @Autowired
    private IAuthenticationService customerService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerCustomer(@RequestBody RegisterRequest customerRequest) {

        return ResponseEntity.ok(customerService.register(customerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authentication(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        LoginResponse loginResponse = customerService.login(loginRequest, response);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponse> logout(
            @CookieValue(name = "refreshToken") String refreshToken
    ) throws IOException {
        return ResponseEntity.ok(customerService.logout(refreshToken));
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
