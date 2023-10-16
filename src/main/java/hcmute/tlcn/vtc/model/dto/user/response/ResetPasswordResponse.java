package hcmute.tlcn.vtc.model.dto.user.response;

import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
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
