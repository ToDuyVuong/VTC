package hcmute.tlcn.vtc.model.dto.user.response;

import hcmute.tlcn.vtc.model.dto.AddressDTO;
import hcmute.tlcn.vtc.model.dto.CustomerDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
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
