package hcmute.tlcn.vtc.dto.user.response;


import hcmute.tlcn.vtc.dto.AddressDTO;
import hcmute.tlcn.vtc.dto.CustomerDTO;
import hcmute.tlcn.vtc.dto.extra.ResponseAbstract;
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
