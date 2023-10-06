package hcmute.tlcn.vtc.service;

import hcmute.tlcn.vtc.dto.user.response.SendEmailForgotPasswordResponse;

public interface IMailService {


    SendEmailForgotPasswordResponse sendMailForgotPassword(String username);
}
