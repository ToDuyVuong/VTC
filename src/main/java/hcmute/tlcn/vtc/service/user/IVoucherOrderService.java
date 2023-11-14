package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.entity.Order;
import hcmute.tlcn.vtc.model.entity.VoucherOrder;
import org.springframework.transaction.annotation.Transactional;

public interface IVoucherOrderService {


    @Transactional
    VoucherOrder saveVoucherOrder(Long voucherId, Order order, boolean isShop);

    Long calculateVoucher(Long voucherId, Long shopId, Long totalPrice, boolean isShop);
}
