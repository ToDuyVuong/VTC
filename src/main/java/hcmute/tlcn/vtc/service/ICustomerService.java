package hcmute.tlcn.vtc.service;

import hcmute.tlcn.vtc.dto.user.request.LoginRequest;
import hcmute.tlcn.vtc.dto.user.request.RegisterRequest;
import hcmute.tlcn.vtc.dto.user.response.LoginResponse;
import hcmute.tlcn.vtc.dto.user.response.RefreshTokenResponse;
import hcmute.tlcn.vtc.dto.user.response.RegisterResponse;
import hcmute.tlcn.vtc.entity.Customer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ICustomerService {
    RegisterResponse register(RegisterRequest customerRequest);

    LoginResponse login(LoginRequest loginRequest, HttpServletResponse response);


    RefreshTokenResponse refreshToken(
            String refreshToken,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException;
}
