package hcmute.tlcn.vtc.authentication;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}