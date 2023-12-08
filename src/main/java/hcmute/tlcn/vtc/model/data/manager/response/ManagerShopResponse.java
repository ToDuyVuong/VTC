package hcmute.tlcn.vtc.model.data.manager.response;

import hcmute.tlcn.vtc.model.dto.ShopDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ManagerShopResponse extends ResponseAbstract {

    private ShopDTO shopDTO;

}
