package hcmute.tlcn.vtc.service.guest;

import hcmute.tlcn.vtc.model.data.guest.ListVoucherResponse;
import hcmute.tlcn.vtc.model.data.guest.VoucherResponse;
import hcmute.tlcn.vtc.model.entity.vtc.Voucher;
import hcmute.tlcn.vtc.model.extra.VoucherType;

import java.util.List;

public interface IVoucherService {
    VoucherResponse getVoucherByVoucherId(Long voucherId);

    ListVoucherResponse listVoucherByShopId(Long shopId);

    ListVoucherResponse listVoucherByType(VoucherType type);

    ListVoucherResponse listVoucherSystem();

    ListVoucherResponse allVoucher();

    VoucherResponse voucherResponse(Voucher voucher);

    ListVoucherResponse listVoucherResponse(List<Voucher> vouchers, String message);

    Voucher getVoucherById(Long voucherId);
}
