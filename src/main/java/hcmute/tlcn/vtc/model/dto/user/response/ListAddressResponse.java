package hcmute.tlcn.vtc.model.dto.user.response;


import hcmute.tlcn.vtc.model.dto.AddressDTO;
import hcmute.tlcn.vtc.model.dto.CustomerDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListAddressResponse extends ResponseAbstract {

    private List<AddressDTO> addressDTOs;
    private CustomerDTO customerDTO;
}
