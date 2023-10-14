package hcmute.tlcn.vtc.dto.user.response;

import hcmute.tlcn.vtc.dto.AddressDTO;
import hcmute.tlcn.vtc.dto.CustomerDTO;
import hcmute.tlcn.vtc.dto.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse extends ResponseAbstract {

    private AddressDTO addressDTO;
    private CustomerDTO customerDTO;
}
