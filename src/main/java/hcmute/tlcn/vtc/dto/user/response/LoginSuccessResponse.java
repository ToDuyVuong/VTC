package hcmute.tlcn.vtc.dto.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginSuccessResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;

    private String username;
    private String fullName;
    private String email;
    private String phone;

    private String status;
    private String message;
    private String code;


}
