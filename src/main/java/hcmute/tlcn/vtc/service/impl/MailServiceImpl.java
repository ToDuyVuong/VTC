package hcmute.tlcn.vtc.service.impl;

import hcmute.tlcn.vtc.dto.MailDTO;
import hcmute.tlcn.vtc.dto.user.response.ForgotPasswordResponse;
import hcmute.tlcn.vtc.model.extra.entity.Customer;
import hcmute.tlcn.vtc.service.ICustomerService;
import hcmute.tlcn.vtc.service.IMailService;
import hcmute.tlcn.vtc.service.IOtpService;
import hcmute.tlcn.vtc.util.exception.EmailSendingException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements IMailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IOtpService otpService;


    private void sendEmail(MailDTO mail) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom()));
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setText(mail.getMailContent());
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            throw new EmailSendingException("Gửi email thất bại. " + e.getMessage());
        }
    }

    @Override
    public ForgotPasswordResponse sendMailForgotPassword(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Tài khoản không được để trống.");
        }

        Customer customer = customerService.getCustomerByUsername(username);
        String otp = otpService.generateOtp(username);

        MailDTO mail = new MailDTO();
        mail.setMailFrom("conc5288@gmail.com");
        mail.setMailTo(customer.getEmail());
        mail.setMailSubject("Lấy lại mật khẩu của tài khoản "
                + customer.getUsername()
                + " trên hệ thống sàn bách hóa VTC.");
        mail.setMailContent("Mã OTP lấy lại mật khẩu của bạn là: " + otp);

        try {
            sendEmail(mail);
        } catch (EmailSendingException e) {
            throw new RuntimeException("Có lỗi xảy ra khi gửi email: " + e.getMessage());
        }

        ForgotPasswordResponse response = new ForgotPasswordResponse();
        response.setEmail(customer.getEmail());
        response.setUsername(customer.getUsername());
        response.setTimeValid(otpService.getTimeValid(username));
        response.setMessage("Thông báo: Gửi mã OTP lấy lại mật khẩu về email: "
                + customer.getEmail()
                + " của tài khoản "
                + customer.getUsername()
                + " thành công. Mã OTP có hiệu lực trong vòng 5 phút.");
        response.setStatus("ok");
        response.setCode(200);

        return response;
    }


    @Override
    public boolean sendNewPasswordToEmail(String newPassword, String email, String username) {
        MailDTO mail = new MailDTO();
        mail.setMailFrom("conc5288@gmail.com");
        mail.setMailTo(email);
        mail.setMailSubject("Thông báo mật khẩu mới của tài khoản "
                + username
                + " trên hệ thống sàn bách hóa VTC.");
        mail.setMailContent("Mật khẩu mới của bạn là: " + newPassword);

        try {
            sendEmail(mail);
        } catch (EmailSendingException e) {
            throw new RuntimeException("Có lỗi xảy ra khi gửi email: " + e.getMessage());
        }
        return true;
    }


}
