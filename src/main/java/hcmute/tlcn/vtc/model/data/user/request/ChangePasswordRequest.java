package hcmute.tlcn.vtc.model.data.user.request;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {

    private String username;

    private String oldPassword;

    private String newPassword;

    public void validate() {

        if (oldPassword == null || oldPassword.isEmpty()) {
            throw new IllegalArgumentException("Mật khẩu cũ không được để trống.");
        }

        if (newPassword == null || newPassword.isEmpty()) {
            throw new IllegalArgumentException("Mật khẩu mới không được để trống.");
        }

        trim();
    }

    public void trim() {
        this.username = this.username.trim();
        this.oldPassword = this.oldPassword.trim();
        this.newPassword = this.newPassword.trim();
    }
}
