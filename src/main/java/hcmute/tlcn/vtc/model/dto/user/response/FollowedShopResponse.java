package hcmute.tlcn.vtc.model.dto.user.response;

import hcmute.tlcn.vtc.model.dto.FollowedShopDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FollowedShopResponse extends ResponseAbstract {


    private FollowedShopDTO followedShopDTO;
}
