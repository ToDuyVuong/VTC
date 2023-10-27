package hcmute.tlcn.vtc.service.vendor;

import hcmute.tlcn.vtc.model.data.vendor.request.CategoryShopRequest;
import hcmute.tlcn.vtc.model.data.vendor.response.ListCategoryShopResponse;
import hcmute.tlcn.vtc.model.data.vendor.response.CategoryShopResponse;
import hcmute.tlcn.vtc.model.entity.Category;
import hcmute.tlcn.vtc.model.extra.Status;

public interface ICategoryShopService {
    CategoryShopResponse addNewCategoryShop(CategoryShopRequest request);


    ListCategoryShopResponse getListCategoryShop(String username);


    CategoryShopResponse getCategoryById(Long categoryId, String username);

    CategoryShopResponse updateCategoryShop(CategoryShopRequest request);


    CategoryShopResponse updateStatusCategoryShop(Long categoryId, String username, Status status);


    Category getCategoryShopById(Long categoryId, String username);
}
