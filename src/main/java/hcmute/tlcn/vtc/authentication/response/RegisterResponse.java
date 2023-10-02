package hcmute.tlcn.vtc.authentication.response;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {

        private String username;
        private String email;
        private String status;
        private String message;
}
