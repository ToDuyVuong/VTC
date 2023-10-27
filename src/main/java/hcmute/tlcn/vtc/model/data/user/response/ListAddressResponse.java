package hcmute.tlcn.vtc.model.data.user.response;


import hcmute.tlcn.vtc.model.data.dto.AddressDTO;
import hcmute.tlcn.vtc.model.data.dto.CustomerDTO;
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
