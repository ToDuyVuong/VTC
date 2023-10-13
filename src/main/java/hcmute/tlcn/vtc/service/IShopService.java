package hcmute.tlcn.vtc.service;

import hcmute.tlcn.vtc.dto.vendor.request.RegisterShopRequest;
import hcmute.tlcn.vtc.dto.vendor.request.UpdateShopRequest;
import hcmute.tlcn.vtc.dto.vendor.response.ShopResponse;
import hcmute.tlcn.vtc.entity.extra.Status;

public interface IShopService {
    ShopResponse registerShop(RegisterShopRequest request);

    ShopResponse getProfileShop(String username);

    ShopResponse updateShop(UpdateShopRequest request);

    ShopResponse updateStatusShop (String username, Status status);
}
