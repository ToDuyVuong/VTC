package hcmute.tlcn.vtc.service;

import hcmute.tlcn.vtc.dto.admin.request.CategoryAdminRequest;
import hcmute.tlcn.vtc.dto.admin.response.AllCategoryAdminResponse;
import hcmute.tlcn.vtc.dto.admin.response.CategoryAdminResponse;

public interface ICategoryAdminService {
    CategoryAdminResponse addNewCategory (CategoryAdminRequest request);

    AllCategoryAdminResponse getParentCategory();
}
