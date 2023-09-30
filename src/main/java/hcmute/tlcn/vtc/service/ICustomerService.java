package hcmute.tlcn.vtc.service;

import hcmute.tlcn.vtc.dto.user.request.LoginRequest;
import hcmute.tlcn.vtc.dto.user.request.RegisterCustomerRequest;
import hcmute.tlcn.vtc.dto.user.response.LoginSuccessResponse;
import hcmute.tlcn.vtc.dto.user.response.RegisterSuccessResponse;
import hcmute.tlcn.vtc.entity.Customer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ICustomerService {
    RegisterSuccessResponse registerCustomer(RegisterCustomerRequest customerRequest);

    LoginSuccessResponse loginCustomer(LoginRequest loginRequest);

    void saveCustomerToken(Customer customer, String jwtToken);

    void revokeAllCustomerTokens(Customer customer);

    void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException;
}
