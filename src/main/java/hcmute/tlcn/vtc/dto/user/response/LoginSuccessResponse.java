package hcmute.tlcn.vtc.dto.user.response;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginSuccessResponse {

    private String token;
    private String username;
    private String fullName;
    private String email;
    private String phone;


}
