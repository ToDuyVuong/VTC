package hcmute.tlcn.vtc.dto.user.response;


import hcmute.tlcn.vtc.authentication.response.LoginResponse;
import hcmute.tlcn.vtc.dto.AddressDTO;
import hcmute.tlcn.vtc.dto.CustomerDTO;
import hcmute.tlcn.vtc.dto.ResponseAbstract;
import hcmute.tlcn.vtc.entity.extra.Status;
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
