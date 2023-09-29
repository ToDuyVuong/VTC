package hcmute.tlcn.vtc.service;

import hcmute.tlcn.vtc.dto.user.request.LoginRequest;
import hcmute.tlcn.vtc.dto.user.request.RegisterCustomerRequest;
import hcmute.tlcn.vtc.dto.user.response.LoginSuccessResponse;
import hcmute.tlcn.vtc.dto.user.response.RegisterSuccessResponse;

public interface ICustomerService {
    RegisterSuccessResponse registerCustomer(RegisterCustomerRequest customerRequest);

    LoginSuccessResponse loginCustomer(LoginRequest loginRequest);
}
