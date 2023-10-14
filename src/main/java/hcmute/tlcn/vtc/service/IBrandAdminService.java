package hcmute.tlcn.vtc.service;

import hcmute.tlcn.vtc.dto.admin.request.BrandAdminRequest;
import hcmute.tlcn.vtc.dto.admin.response.AllBrandAdminResponse;
import hcmute.tlcn.vtc.dto.admin.response.BrandAdminResponse;

public interface IBrandAdminService {
    BrandAdminResponse addNewBrand(BrandAdminRequest request);

    BrandAdminResponse getBrandById(Long brandId);

    AllBrandAdminResponse getAllBrandAdmin();


    BrandAdminResponse updateBrandAdmin(BrandAdminRequest request);
}
