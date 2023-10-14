package hcmute.tlcn.vtc.dto.user.response;

import hcmute.tlcn.vtc.dto.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordResponse extends ResponseAbstract {

    private String username;

    private String email;

    private long timeValid;
}
