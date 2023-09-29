package hcmute.tlcn.vtc.dto.user.response;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterSuccessResponse {

        private String username;
        private String email;
        private String status;
        private String message;
}
