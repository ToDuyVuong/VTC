package hcmute.tlcn.vtc.service;

import hcmute.tlcn.vtc.dto.admin.request.CategoryAdminRequest;
import hcmute.tlcn.vtc.dto.vendor.response.CategoryResponse;

public interface ICategoryService {
    CategoryResponse addNewCategory (CategoryAdminRequest request);
}
