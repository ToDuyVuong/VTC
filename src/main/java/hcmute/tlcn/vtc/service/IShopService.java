package hcmute.tlcn.vtc.service;

import hcmute.tlcn.vtc.dto.vendor.request.RegisterShopRequest;
import hcmute.tlcn.vtc.dto.vendor.response.ShopResponse;

public interface IShopService {
    ShopResponse registerShop(RegisterShopRequest request);
}
