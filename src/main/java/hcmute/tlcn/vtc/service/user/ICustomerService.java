package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.data.user.request.ChangePasswordRequest;
import hcmute.tlcn.vtc.model.data.user.request.ForgotPasswordRequest;
import hcmute.tlcn.vtc.model.data.user.request.ProfileCustomerRequest;
import hcmute.tlcn.vtc.model.data.user.response.ForgotPasswordResponse;
import hcmute.tlcn.vtc.model.data.user.response.ProfileCustomerResponse;
import hcmute.tlcn.vtc.model.entity.Customer;

public interface ICustomerService {
    Customer getCustomerByUsername(String username);


    ProfileCustomerResponse getProfileCustomer(String token);


    ProfileCustomerResponse updateProfileCustomer(ProfileCustomerRequest profileCustomerRequest);

    ProfileCustomerResponse changePassword(ChangePasswordRequest request);


    ForgotPasswordResponse resetPassword(ForgotPasswordRequest request );
}
