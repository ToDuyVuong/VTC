package hcmute.tlcn.vtc.service.vendor;

import hcmute.tlcn.vtc.model.dto.vendor.request.CategoryShopRequest;
import hcmute.tlcn.vtc.model.dto.vendor.response.AllCategoryShopResponse;
import hcmute.tlcn.vtc.model.dto.vendor.response.CategoryShopResponse;
import hcmute.tlcn.vtc.model.extra.Status;

public interface ICategoryShopService {
    CategoryShopResponse addNewCategoryShop(CategoryShopRequest request);

    AllCategoryShopResponse getAllCategoryByShopId(Long shopId);

    CategoryShopResponse getCategoryById(Long categoryId, Long shopId);

    CategoryShopResponse updateCategoryShop(CategoryShopRequest request);

    CategoryShopResponse updateStatusCategoryShop(Long categoryId, Long shopId, Status status);
}
