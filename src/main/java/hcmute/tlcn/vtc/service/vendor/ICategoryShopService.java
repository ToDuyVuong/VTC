package hcmute.tlcn.vtc.service.vendor;

import hcmute.tlcn.vtc.model.dto.vendor.request.CategoryShopRequest;
import hcmute.tlcn.vtc.model.dto.vendor.response.AllCategoryShopResponse;
import hcmute.tlcn.vtc.model.dto.vendor.response.CategoryShopResponse;

public interface ICategoryShopService {
    CategoryShopResponse addNewCategoryShop(CategoryShopRequest request);

    AllCategoryShopResponse getAllCategoryByShopId(Long shopId);
}
