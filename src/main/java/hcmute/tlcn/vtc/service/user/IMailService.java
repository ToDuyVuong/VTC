package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.dto.user.response.ForgotPasswordResponse;

public interface IMailService {


    ForgotPasswordResponse sendMailForgotPassword(String username);

    boolean sendNewPasswordToEmail(String newPassword, String email, String username);
}
