package hcmute.tlcn.vtc.authentication.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import hcmute.tlcn.vtc.dto.CustomerDTO;
import hcmute.tlcn.vtc.dto.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse extends ResponseAbstract {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;

    private CustomerDTO customerDTO;



}
