package hcmute.tlcn.vtc.service;

import hcmute.tlcn.vtc.dto.admin.request.CategoryAdminRequest;
import hcmute.tlcn.vtc.dto.admin.response.AllCategoryAdminResponse;
import hcmute.tlcn.vtc.dto.admin.response.CategoryAdminResponse;
import hcmute.tlcn.vtc.dto.vendor.response.CategoryResponse;
import hcmute.tlcn.vtc.entity.extra.Status;

public interface ICategoryAdminService {
    CategoryAdminResponse addNewCategory (CategoryAdminRequest request);

    CategoryAdminResponse getCategoryParent(Long categoryId);

    AllCategoryAdminResponse getParentCategory();

    CategoryAdminResponse updateCategoryParent(CategoryAdminRequest request);

    CategoryAdminResponse updateStatusCategoryParent(Long categoryId, Status status);
}
