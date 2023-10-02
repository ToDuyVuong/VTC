package hcmute.tlcn.vtc.authentication.service;

import hcmute.tlcn.vtc.authentication.request.LoginRequest;
import hcmute.tlcn.vtc.authentication.request.RegisterRequest;
import hcmute.tlcn.vtc.authentication.response.LoginResponse;
import hcmute.tlcn.vtc.authentication.response.RefreshTokenResponse;
import hcmute.tlcn.vtc.authentication.response.RegisterResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface IAuthenticationService {
    RegisterResponse register(RegisterRequest customerRequest);

    LoginResponse login(LoginRequest loginRequest, HttpServletResponse response);


    RefreshTokenResponse refreshToken(
            String refreshToken,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException;

//    LogoutResponse logout(HttpServletRequest request, HttpServletResponse response);

}
