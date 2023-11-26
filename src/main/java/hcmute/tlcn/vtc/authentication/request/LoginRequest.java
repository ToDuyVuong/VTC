package hcmute.tlcn.vtc.authentication.request;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    private String username;

    private String password;

    public void validate() {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Tài khoản không được để trống.");
        }

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Mật khẩu không được để trống.");
        }

        trim();
    }

    public void trim() {
        this.username = this.username.trim();
        this.password = this.password.trim();
    }
}
