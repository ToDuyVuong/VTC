package hcmute.tlcn.vtc.service.vendor.impl;

import hcmute.tlcn.vtc.model.entity.Cart;
import hcmute.tlcn.vtc.model.entity.Order;
import hcmute.tlcn.vtc.model.entity.OrderItem;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.repository.CartRepository;
import hcmute.tlcn.vtc.repository.OrderItemRepository;
import hcmute.tlcn.vtc.repository.OrderRepository;
import hcmute.tlcn.vtc.service.admin.IVoucherAdminService;
import hcmute.tlcn.vtc.service.user.*;
import hcmute.tlcn.vtc.service.vendor.IOrderItemShopService;
import hcmute.tlcn.vtc.service.vendor.IShopService;
import hcmute.tlcn.vtc.service.vendor.IVoucherShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemShopServiceImpl implements IOrderItemShopService {

    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final IOrderItemService orderItemService;
    @Autowired
    private final IVoucherOrderService voucherOrderService;
    @Autowired
    private final ICartService cartService;
    @Autowired
    private final ICustomerService customerService;
    @Autowired
    private final IAddressService addressService;
    @Autowired
    private final IVoucherShopService voucherShopService;
    @Autowired
    private final IVoucherAdminService voucherSystemService;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private final IShopService shopService;
    @Autowired
    private final IOrderService orderService;

    @Transactional
    @Override
    public List<OrderItem> updateStatusOrderItemByShop(Order order, Status status) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItem orderItem : order.getOrderItems()) {
            Cart cart = orderItem.getCart();
            cart.setStatus(status);
            cart.setUpdateAt(order.getUpdateAt());
            try {
                orderItem.setCart(cartRepository.save(cart));
                orderItems.add(orderItem);
            } catch (Exception e) {
                throw new IllegalArgumentException("Cập nhật trạng thái đơn hàng thất bại!");
            }
        }
        return orderItems;
    }
}
