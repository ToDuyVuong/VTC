package hcmute.tlcn.vtc.authentication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationResponse {
    private String token;
//    private String accessToken;
//    private String refreshToken;
//    private String tokenType;
//    private Long expiresIn;
}