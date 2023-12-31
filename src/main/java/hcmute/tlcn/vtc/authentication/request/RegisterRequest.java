package hcmute.tlcn.vtc.authentication.request;

import hcmute.tlcn.vtc.model.extra.EmailValidator;
import lombok.*;

import java.util.Date;
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String username;

    private String password;

    private String email;

    private boolean gender;

    private String fullName;

    private Date birthday;

//    private String phone;
//
//    private Role role;

    public void validate() {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Tài khoản không được để trống.");
        }

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Mật khẩu không được để trống.");
        }

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email không được để trống.");
        } else if (!EmailValidator.isValidEmail(email)) {
            throw new IllegalArgumentException("Email không hợp lệ.");
        }

        if (fullName == null || fullName.isEmpty()) {
            throw new IllegalArgumentException("Tên đầy đủ không được để trống.");
        }

        if (birthday == null) {
            throw new IllegalArgumentException("Ngày sinh không được để trống.");
        }

        trim();

    }
    public void trim() {
        this.username = this.username.trim();
        this.password = this.password.trim();
        this.email = this.email.trim();
        this.fullName = this.fullName.trim();
    }




}
