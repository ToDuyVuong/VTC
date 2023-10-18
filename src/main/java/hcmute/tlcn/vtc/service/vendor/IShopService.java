package hcmute.tlcn.vtc.service.vendor;

import hcmute.tlcn.vtc.model.dto.vendor.request.RegisterShopRequest;
import hcmute.tlcn.vtc.model.dto.vendor.request.UpdateShopRequest;
import hcmute.tlcn.vtc.model.dto.vendor.response.ShopResponse;
import hcmute.tlcn.vtc.model.entity.Shop;
import hcmute.tlcn.vtc.model.extra.Status;

public interface IShopService {
    ShopResponse registerShop(RegisterShopRequest request);

    ShopResponse getProfileShop(String username);

    ShopResponse updateShop(UpdateShopRequest request);

    ShopResponse updateStatusShop (String username, Status status);

    Shop checkShop(Long shopId);
}
