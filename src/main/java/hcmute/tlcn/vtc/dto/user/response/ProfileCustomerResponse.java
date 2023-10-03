package hcmute.tlcn.vtc.dto.user.response;

import hcmute.tlcn.vtc.dto.CustomerDTO;
import hcmute.tlcn.vtc.dto.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCustomerResponse extends ResponseAbstract {


    private CustomerDTO customerDTO;

}
