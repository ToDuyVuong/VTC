package hcmute.tlcn.vtc.model.dto.user.response;

import hcmute.tlcn.vtc.model.dto.CustomerDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCustomerResponse extends ResponseAbstract {

    private CustomerDTO customerDTO;

}
