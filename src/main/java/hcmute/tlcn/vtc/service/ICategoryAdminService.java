package hcmute.tlcn.vtc.service;

import hcmute.tlcn.vtc.dto.admin.request.CategoryAdminRequest;
import hcmute.tlcn.vtc.dto.admin.response.AllCategoryAdminResponse;
import hcmute.tlcn.vtc.dto.admin.response.CategoryAdminResponse;
import hcmute.tlcn.vtc.dto.vendor.response.CategoryResponse;

public interface ICategoryAdminService {
    CategoryAdminResponse addNewCategory (CategoryAdminRequest request);

    AllCategoryAdminResponse getParentCategory();
}
