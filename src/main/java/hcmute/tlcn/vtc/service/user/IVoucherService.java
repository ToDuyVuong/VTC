package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.data.user.response.ListVoucherResponse;
import hcmute.tlcn.vtc.model.data.user.response.VoucherResponse;
import hcmute.tlcn.vtc.model.extra.VoucherType;

public interface IVoucherService {
    VoucherResponse getVoucherByVoucherId(Long voucherId);

    ListVoucherResponse listVoucherByShopId(Long shopId);

    ListVoucherResponse listVoucherByType(VoucherType type);

    ListVoucherResponse listVoucherSystem();

    ListVoucherResponse allVoucher();
}
