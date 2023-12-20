package hcmute.tlcn.vtc.model.data.vendor.response;

import hcmute.tlcn.vtc.model.dto.CustomerDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import hcmute.tlcn.vtc.model.dto.ShopDTO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShopResponse extends ResponseAbstract {

    private ShopDTO shopDTO;
    private CustomerDTO customerDTO;
}
