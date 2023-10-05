package hcmute.tlcn.vtc.dto.user.request;

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
}
