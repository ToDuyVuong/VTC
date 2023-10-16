package hcmute.tlcn.vtc.service.admin;

import hcmute.tlcn.vtc.model.dto.admin.request.BrandAdminRequest;
import hcmute.tlcn.vtc.model.dto.admin.response.AllBrandAdminResponse;
import hcmute.tlcn.vtc.model.dto.admin.response.BrandAdminResponse;
import hcmute.tlcn.vtc.model.extra.Status;

public interface IBrandAdminService {
    BrandAdminResponse addNewBrand(BrandAdminRequest request);

    BrandAdminResponse getBrandById(Long brandId);

    AllBrandAdminResponse getAllBrandAdmin();

    BrandAdminResponse updateBrandAdmin(BrandAdminRequest request);

    BrandAdminResponse updateStatusBrandAdmin(Long brandId, String username, Status status);
}
