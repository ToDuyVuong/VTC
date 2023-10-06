package hcmute.tlcn.vtc.service;

import hcmute.tlcn.vtc.dto.CustomerDTO;
import hcmute.tlcn.vtc.dto.user.request.ChangePasswordRequest;
import hcmute.tlcn.vtc.dto.user.request.ForgotPasswordRequest;
import hcmute.tlcn.vtc.dto.user.request.ProfileCustomerRequest;
import hcmute.tlcn.vtc.dto.user.response.ForgotPasswordResponse;
import hcmute.tlcn.vtc.dto.user.response.ProfileCustomerResponse;
import hcmute.tlcn.vtc.entity.Customer;

public interface ICustomerService {
    Customer getCustomerByUsername(String username);


    ProfileCustomerResponse getProfileCustomer(String token);


    ProfileCustomerResponse updateProfileCustomer(ProfileCustomerRequest profileCustomerRequest);

    ProfileCustomerResponse changePassword(ChangePasswordRequest request);


    ForgotPasswordResponse resetPassword(ForgotPasswordRequest request );
}
