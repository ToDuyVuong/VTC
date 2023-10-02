package hcmute.tlcn.vtc.dto.user.response;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenResponse {

        private String accessToken;

        private String status;
        private String message;
        private int code;
}
