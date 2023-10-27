package hcmute.tlcn.vtc.service.guest;

import hcmute.tlcn.vtc.model.data.guest.ShopDetailResponse;

public interface IShopDetailService {


    ShopDetailResponse getShopDetailByShopId(Long shopId, String username);
}
