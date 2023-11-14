package hcmute.tlcn.vtc.service.user.impl;

import hcmute.tlcn.vtc.model.data.user.request.CreateOrderUpdateRequest;
import hcmute.tlcn.vtc.model.data.user.response.OrderResponse;
import hcmute.tlcn.vtc.model.dto.OrderDTO;
import hcmute.tlcn.vtc.model.entity.*;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.model.extra.VoucherType;
import hcmute.tlcn.vtc.repository.CartRepository;
import hcmute.tlcn.vtc.repository.OrderItemRepository;
import hcmute.tlcn.vtc.repository.OrderRepository;
import hcmute.tlcn.vtc.service.admin.IVoucherAdminService;
import hcmute.tlcn.vtc.service.user.*;
import hcmute.tlcn.vtc.service.vendor.IVoucherShopService;
import hcmute.tlcn.vtc.shippingstrategy.GiaoHangHoaTocShipping;
import hcmute.tlcn.vtc.shippingstrategy.GiaoHangNhanhShipping;
import hcmute.tlcn.vtc.shippingstrategy.GiaoHangTietKiemShipping;
import hcmute.tlcn.vtc.shippingstrategy.IShipping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

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


    @Override
    public OrderResponse createOrder(String username, List<Long> cartIds) {
        Order order = createTemporaryOrder(username, cartIds);
        return orderResponse(username, order, "Tạo đơn hàng mới thành công.", true);
    }


    @Override
    public OrderResponse createOrderUpdate(CreateOrderUpdateRequest request) {
        Order order = createTemporaryOrderUpdate(request);


        return orderResponse(request.getUsername(), order, "Cập nhật đơn hàng thành công.", false);
    }


    @Transactional
    @Override
    public OrderResponse saveOrder(CreateOrderUpdateRequest request) {
        Order order = createTemporaryOrderUpdate(request);


        // Sau này nếu có nhiều hình thước thanh toán sẻ sử lý ở đây.
        switch (order.getPaymentMethod()) {
            case "COD":
                order.setStatus(Status.PENDING);
                break;
            default:
                order.setStatus(Status.PENDING);
                break;
        }

        order.setCreateAt(LocalDateTime.now());
        order.setUpdateAt(LocalDateTime.now());

        try {
            Order save = orderRepository.save(order);

            if (request.getVoucherShopId() != null) {
                VoucherOrder voucherOrder = voucherOrderService.saveVoucherOrder(request.getVoucherShopId(), save, true);
                save.setVoucherOrders(List.of(voucherOrder));
            }

            if (request.getVoucherSystemId() != null) {
                VoucherOrder voucherOrder = voucherOrderService.saveVoucherOrder(request.getVoucherSystemId(), save, false);
                if (save.getVoucherOrders() != null) {
                    save.getVoucherOrders().add(voucherOrder);
                } else {
                    save.setVoucherOrders(List.of(voucherOrder));
                }
            }

            List<OrderItem> orderItems = orderItemService.saveOrderItem(save);
            save.setOrderItems(orderItems);

            return orderResponse(request.getUsername(), save, "Đặt hàng thành công.", false);
        } catch (Exception e) {
            throw new IllegalArgumentException("Đặt hàng thất bại!");
        }
    }


    private Order createTemporaryOrderUpdate(CreateOrderUpdateRequest request) {
        Order order = createTemporaryOrder(request.getUsername(), request.getCartIds());


        if (request.getAddressId() != null && request.getAddressId().equals(order.getAddress().getAddressId())) {
            order.setAddress(addressService.getAddressByIdAndUsername(request.getAddressId(), request.getUsername()));
        }

//        order.setPaymentMethod(request.getPaymentMethod());

        if (request.getShippingMethod().equals(order.getShippingMethod())) {
            order.setShippingMethod(request.getShippingMethod());
            order.setShippingFee(calculateShippingFee(order.getShippingMethod(), order.getTotalPrice()));
        }

        if (!request.getNote().isEmpty()) {
            order.setNote(request.getNote());
        }

        if (request.getVoucherShopId() != null) {
            Long discount = voucherOrderService.calculateVoucher(request.getVoucherShopId(), order.getShopId(), order.getTotalPrice(), true);
            order.setDiscount(order.getDiscount() + discount);
        }

        if (request.getVoucherSystemId() != null) {
            Long discount = voucherOrderService.calculateVoucher(request.getVoucherSystemId(), null, order.getTotalPrice(), false);
            order.setDiscount(order.getDiscount() + discount);
        }

        order.setPaymentTotal(order.getTotalPrice() + order.getShippingFee() - order.getDiscount());
        order.setTotalPrice(order.getTotalPrice());

        return order;
    }

//    private Long calculateVoucher(Long voucherId, Long shopId, Long totalPrice, boolean isShop) {
//        Voucher voucher;
//        if (isShop) {
//            voucher = voucherShopService.checkVoucherShop(voucherId, shopId);
//        } else {
//            voucher = voucherSystemService.checkVoucherSystem(voucherId);
//        }
//
//        if (voucher.getType().equals(VoucherType.SHIPPING)) {
//            return voucher.getDiscount();
//        }
//
//        if (voucher.getType().equals(VoucherType.PERCENTAGE_SHOP)) {
//            return voucher.getDiscount() * totalPrice / 100;
//        }
//        return voucher.getDiscount();
//    }


    private Long calculateShippingFee(String shippingMethod, Long totalPrice) {
        IShipping shippingStrategy = null;
        if (shippingMethod.equals("GHTK")) {
            shippingStrategy = new GiaoHangTietKiemShipping();
        } else {
            shippingStrategy = new GiaoHangHoaTocShipping();
        }
        return shippingStrategy.calculateShippingCost(totalPrice);
    }

    private Order createTemporaryOrder(String username, List<Long> cartIds) {

        Customer customer = customerService.getCustomerByUsername(username);

        checkListCartSameShop(username, cartIds);

        List<OrderItem> orderItems = orderItemService.createOrderItems(username, cartIds);
        Address address = addressService.getAddressActiveByUsername(username);

        Long totalPrice = getTotalPrice(orderItems);
        Long discount = 0L;

        IShipping shippingStrategy = new GiaoHangNhanhShipping();
        Long shippingFee = shippingStrategy.calculateShippingCost(totalPrice);

        Long totalPayment = totalPrice + shippingFee - discount;
        Long shopId = orderItems.get(0).getCart().getProductVariant().getProduct().getCategory().getShop().getShopId();
        String shopName = orderItems.get(0).getCart().getProductVariant().getProduct().getCategory().getShop().getName();

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderItems(orderItems);
        order.setAddress(address);
        order.setStatus(Status.CART);
        order.setTotalPrice(totalPrice);
        order.setVoucherOrders(null);
        order.setPaymentMethod("COD");
        order.setShippingMethod("GHN");
        order.setPaymentTotal(totalPayment);
        order.setDiscount(discount);
        order.setNote(null);
        order.setOrderDate(new Date());
        order.setOrderItems(orderItems);
        order.setCount(getTotalCount(orderItems));
        order.setShopId(shopId);
        order.setShopName(shopName);
        order.setShippingFee(shippingFee);
        return order;
    }


    private OrderResponse orderResponse(String username, Order order, String message, boolean created) {

        OrderDTO orderDTO = OrderDTO.convertEntityToDTOCreate(order);

        OrderResponse response = new OrderResponse();
        response.setUsername(username);
        response.setOrderDTO(orderDTO);
        response.setMessage(message);
        response.setStatus(created ? "ok" : "success");
        response.setCode(200);
        return response;
    }


    private Long getTotalPrice(List<OrderItem> orderItems) {
        long totalPrice = 0L;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getCart().getProductVariant().getPrice() * orderItem.getCart().getQuantity();
        }
        return totalPrice;
    }

    private int getTotalCount(List<OrderItem> orderItems) {
        int count = 0;
        for (OrderItem orderItem : orderItems) {
            count += orderItem.getCart().getQuantity();
        }
        return count;
    }

    private void checkListCartSameShop(String username, List<Long> cartIds) {
        boolean check = cartService.checkCartsSameShop(username, cartIds);
        if (!check) {
            throw new IllegalArgumentException("Các sản phẩm không thuộc cùng một cửa hàng.");
        }
    }


}
