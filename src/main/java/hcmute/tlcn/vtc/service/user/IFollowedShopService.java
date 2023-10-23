package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.dto.user.response.FollowedShopResponse;
import hcmute.tlcn.vtc.model.dto.user.response.ListFollowedShopResponse;

public interface IFollowedShopService {
   FollowedShopResponse addNewFollowedShop(Long shopId, String username);

   ListFollowedShopResponse getListFollowedShopByUsername(String username);

   FollowedShopResponse deleteFollowedShop(Long followedShopId, String username);
}
