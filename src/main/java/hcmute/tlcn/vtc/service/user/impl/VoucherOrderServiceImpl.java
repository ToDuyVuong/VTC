package hcmute.tlcn.vtc.service.user.impl;

import hcmute.tlcn.vtc.model.entity.Order;
import hcmute.tlcn.vtc.model.entity.Voucher;
import hcmute.tlcn.vtc.model.entity.VoucherOrder;
import hcmute.tlcn.vtc.model.extra.VoucherType;
import hcmute.tlcn.vtc.repository.VoucherOrderRepository;
import hcmute.tlcn.vtc.repository.VoucherRepository;
import hcmute.tlcn.vtc.service.admin.impl.VoucherAdminServiceImpl;
import hcmute.tlcn.vtc.service.user.IVoucherOrderService;
import hcmute.tlcn.vtc.service.vendor.impl.VoucherShopServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VoucherOrderServiceImpl implements IVoucherOrderService {

    @Autowired
    private final VoucherOrderRepository voucherOrderRepository;
    @Autowired
    private final VoucherRepository voucherRepository;
    @Autowired
    private final VoucherShopServiceImpl voucherShopService;
    @Autowired
    private final VoucherAdminServiceImpl voucherSystemService;


    @Transactional
    @Override
    public VoucherOrder saveVoucherOrder(Long voucherId, Order order, boolean isShop) {
        Voucher voucher;
        if (isShop){
             voucher = voucherShopService.checkVoucherShop(voucherId, order.getShopId());

        }else {
            voucher = voucherSystemService.checkVoucherSystem(voucherId);
        }
        VoucherOrder voucherOrder = new VoucherOrder();
        voucherOrder.setVoucher(voucher);
        voucherOrder.setOrder(order);
        try {
            voucher.setQuantityUsed(voucher.getQuantityUsed() + 1);
            voucherRepository.save(voucher);
            return voucherOrderRepository.save(voucherOrder);
        } catch (Exception e) {
            throw new IllegalArgumentException("Thêm mới mã giảm giá thất bại!");
        }
    }

    @Transactional
    @Override
    public VoucherOrder cancelVoucherOrder(Long voucherOrderId) {
        VoucherOrder voucherOrder = voucherOrderRepository.findById(voucherOrderId)
                .orElseThrow(() -> new IllegalArgumentException("Mã giảm giá không tồn tại!"));

        Voucher voucher = voucherOrder.getVoucher();
        voucher.setQuantityUsed(voucher.getQuantityUsed() - 1);
        try {
            voucherRepository.save(voucher);
            return voucherOrder;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cập nhật mã giảm giá thất bại!");
        }
    }

/*    @Transactional
    @Override
    public VoucherOrder cancelVoucherOrder(Long voucherOrderId, boolean isShop) {
        VoucherOrder voucherOrder = voucherOrderRepository.findById(voucherOrderId)
                .orElseThrow(() -> new IllegalArgumentException("Mã giảm giá không tồn tại!"));

        Voucher voucher;
        if (isShop) {
            voucher = voucherShopService.checkVoucherShop(voucherOrder.getVoucher().getVoucherId(), voucherOrder.getOrder().getShopId());
        }else {
            voucher = voucherSystemService.checkVoucherSystem(voucherOrder.getVoucher().getVoucherId());
        }
        voucher.setQuantityUsed(voucher.getQuantityUsed() - 1);
        try {
            voucherRepository.save(voucher);
            return voucherOrder;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cập nhật mã giảm giá thất bại!");
        }
    }*/


    @Override
    public Long calculateVoucher(Long voucherId, Long shopId, Long totalPrice, boolean isShop) {
        Voucher voucher;
        if (isShop) {
            voucher = voucherShopService.checkVoucherShop(voucherId, shopId);
        } else {
            voucher = voucherSystemService.checkVoucherSystem(voucherId);
        }

        if (voucher.getType().equals(VoucherType.SHIPPING)) {
            return voucher.getDiscount();
        }

        if (voucher.getType().equals(VoucherType.PERCENTAGE_SHOP)) {
            return voucher.getDiscount() * totalPrice / 100;
        }
        return voucher.getDiscount();
    }


}
