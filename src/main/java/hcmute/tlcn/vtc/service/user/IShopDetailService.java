package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.dto.user.response.ShopDetailResponse;

public interface IShopDetailService {


    ShopDetailResponse getShopDetailByShopId(Long shopId, String username);
}
