package hcmute.tlcn.vtc.service.admin;

import hcmute.tlcn.vtc.model.dto.admin.request.CategoryAdminRequest;
import hcmute.tlcn.vtc.model.dto.admin.response.AllCategoryAdminResponse;
import hcmute.tlcn.vtc.model.dto.admin.response.CategoryAdminResponse;
import hcmute.tlcn.vtc.model.extra.Status;

public interface ICategoryAdminService {
    CategoryAdminResponse addNewCategory (CategoryAdminRequest request);

    CategoryAdminResponse getCategoryParent(Long categoryId);

    AllCategoryAdminResponse getAllCategoryParent();

    CategoryAdminResponse updateCategoryParent(CategoryAdminRequest request);

    CategoryAdminResponse updateStatusCategoryParent(Long categoryId, Status status);
}
