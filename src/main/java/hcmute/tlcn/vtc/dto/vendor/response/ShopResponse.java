package hcmute.tlcn.vtc.dto.vendor.response;

import hcmute.tlcn.vtc.dto.extra.ResponseAbstract;
import hcmute.tlcn.vtc.dto.ShopDTO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShopResponse extends ResponseAbstract {

    private ShopDTO shopDTO;
}
