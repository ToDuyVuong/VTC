package hcmute.tlcn.vtc.service.admin;

import hcmute.tlcn.vtc.authentication.request.RegisterRequest;
import hcmute.tlcn.vtc.authentication.response.RegisterResponse;

public interface IAdminService {
    RegisterResponse register(RegisterRequest customerRequest);
}
