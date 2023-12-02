package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.entity.vtc.Order;
import hcmute.tlcn.vtc.model.entity.vtc.VoucherOrder;
import org.springframework.transaction.annotation.Transactional;

public interface IVoucherOrderService {


    @Transactional
    VoucherOrder saveVoucherOrder(Long voucherId, Order order, boolean isShop);

    @Transactional
    VoucherOrder cancelVoucherOrder(Long voucherOrderId);

/*    @Transactional
    VoucherOrder cancelVoucherOrder(Long voucherOrderId, boolean isShop);*/

    Long calculateVoucher(Long voucherId, Long shopId, Long totalPrice, boolean isShop);
}
