package hcmute.tlcn.vtc.service.admin;

import hcmute.tlcn.vtc.model.data.admin.request.VoucherAdminRequest;
import hcmute.tlcn.vtc.model.data.admin.response.ListVoucherAdminResponse;
import hcmute.tlcn.vtc.model.data.admin.response.VoucherAdminResponse;
import hcmute.tlcn.vtc.model.entity.vtc.Voucher;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.model.extra.VoucherType;
import org.springframework.transaction.annotation.Transactional;

public interface IVoucherAdminService{
    VoucherAdminResponse addNewVoucherAdmin(VoucherAdminRequest request);

    VoucherAdminResponse getVoucherAdminByVoucherId(Long voucherId);

    ListVoucherAdminResponse getListVoucherAdmin(String username);

    ListVoucherAdminResponse getListVoucherAdminByUsername(String username);

    ListVoucherAdminResponse getListVoucherAdminByStatus(String username, Status status);

    ListVoucherAdminResponse getListVoucherAdminByType(String username, VoucherType type);

    @Transactional
    VoucherAdminResponse updateVoucherAdmin(VoucherAdminRequest request, String username);

    @Transactional
    VoucherAdminResponse updateStatusVoucherAdmin(Long voucherId, Status status, String username);

    Voucher checkVoucherSystem(Long voucherId);
}
