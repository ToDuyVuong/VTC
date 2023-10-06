package hcmute.tlcn.vtc.dto.user.response;

import hcmute.tlcn.vtc.dto.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordResponse extends ResponseAbstract {

        private String username;

        private String email;

        private String password;

        private String otp;

        private long timeValid;
}
