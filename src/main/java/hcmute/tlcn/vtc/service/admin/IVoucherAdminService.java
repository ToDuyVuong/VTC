package hcmute.tlcn.vtc.service.admin;

import hcmute.tlcn.vtc.model.data.admin.request.VoucherAdminRequest;
import hcmute.tlcn.vtc.model.data.admin.response.VoucherAdminResponse;

public interface IVoucherAdminService{
    VoucherAdminResponse addNewVoucherAdmin(VoucherAdminRequest request);
}
