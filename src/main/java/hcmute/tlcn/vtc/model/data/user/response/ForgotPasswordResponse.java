package hcmute.tlcn.vtc.model.data.user.response;

import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
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
