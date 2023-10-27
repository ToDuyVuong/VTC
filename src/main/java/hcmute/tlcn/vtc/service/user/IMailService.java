package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.data.user.response.ForgotPasswordResponse;

public interface IMailService {


    ForgotPasswordResponse sendMailForgotPassword(String username);

    boolean sendNewPasswordToEmail(String newPassword, String email, String username);
}
