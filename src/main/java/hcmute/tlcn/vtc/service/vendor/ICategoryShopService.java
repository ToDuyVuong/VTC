package hcmute.tlcn.vtc.service.vendor;

import hcmute.tlcn.vtc.model.dto.vendor.request.CategoryShopRequest;
import hcmute.tlcn.vtc.model.dto.vendor.response.ListCategoryShopResponse;
import hcmute.tlcn.vtc.model.dto.vendor.response.CategoryShopResponse;
import hcmute.tlcn.vtc.model.entity.Category;
import hcmute.tlcn.vtc.model.extra.Status;

public interface ICategoryShopService {
    CategoryShopResponse addNewCategoryShop(CategoryShopRequest request);

    ListCategoryShopResponse getAllCategoryByShopId(Long shopId);

    CategoryShopResponse getCategoryById(Long categoryId, Long shopId);

    CategoryShopResponse updateCategoryShop(CategoryShopRequest request);

    CategoryShopResponse updateStatusCategoryShop(Long categoryId, Long shopId, Status status);

    Category checkCategory(Long categoryId, Long shopId);
}
