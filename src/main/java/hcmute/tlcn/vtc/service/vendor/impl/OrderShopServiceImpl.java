package hcmute.tlcn.vtc.service.vendor.impl;

import hcmute.tlcn.vtc.model.data.user.response.ListOrderResponse;
import hcmute.tlcn.vtc.model.data.user.response.OrderResponse;
import hcmute.tlcn.vtc.model.dto.OrderDTO;
import hcmute.tlcn.vtc.model.entity.vtc.Order;
import hcmute.tlcn.vtc.model.entity.vtc.OrderItem;
import hcmute.tlcn.vtc.model.entity.vtc.Shop;
import hcmute.tlcn.vtc.model.entity.vtc.VoucherOrder;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.repository.CartRepository;
import hcmute.tlcn.vtc.repository.OrderItemRepository;
import hcmute.tlcn.vtc.repository.OrderRepository;
import hcmute.tlcn.vtc.service.admin.IVoucherAdminService;
import hcmute.tlcn.vtc.service.user.*;
import hcmute.tlcn.vtc.service.vendor.IOrderItemShopService;
import hcmute.tlcn.vtc.service.vendor.IOrderShopService;
import hcmute.tlcn.vtc.service.vendor.IShopService;
import hcmute.tlcn.vtc.service.vendor.IVoucherShopService;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderShopServiceImpl implements IOrderShopService {

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
    @Autowired
    private final IOrderItemShopService orderItemShopService;


    @Override
    public ListOrderResponse getOrders(String username) {
        Shop shop = shopService.getShopByUsername(username);

        List<Order> orders = orderRepository.findAllByShopId(shop.getShopId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy đơn hàng nào!"));

        return listOrderResponse(orders, "Lấy danh sách đơn hàng thành công!", username);
    }


    @Override
    public ListOrderResponse getOrdersByStatus(String username, Status status) {
        Shop shop = shopService.getShopByUsername(username);

        List<Order> orders = orderRepository.findAllByShopIdAndStatus(shop.getShopId(), status)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy đơn hàng nào!"));

        String message = orderService.messageByOrderStatus(status);

        return listOrderResponse(orders, message, username);
    }


    @Override
    public ListOrderResponse getOrdersOnSameDay(String username, Date orderDate) {
        Shop shop = shopService.getShopByUsername(username);

        Date startOfDay = startOfDay(orderDate);
        Date endOfDay = endOfDay(orderDate);


        List<Order> orders = orderRepository.findAllByShopIdAndOrderDateBetween(shop.getShopId(), startOfDay, endOfDay)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy đơn hàng nào!"));

        return listOrderResponse(orders, "Lấy danh sách đơn hàng trong cùng ngày thành công.", username);
    }

    @Override
    public ListOrderResponse getOrdersOnSameDayByStatus(String username, Date orderDate, Status status) {
        Shop shop = shopService.getShopByUsername(username);

        Date startOfDay = startOfDay(orderDate);
        Date endOfDay = endOfDay(orderDate);

        List<Order> orders = orderRepository.findAllByShopIdAndOrderDateBetweenAndStatus(shop.getShopId(), startOfDay, endOfDay, status)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy đơn hàng nào!"));

        String message = orderService.messageByOrderStatus(status);

        return listOrderResponse(orders, message, username);
    }


    @Override
    public ListOrderResponse getOrdersBetweenDate(String username, Date startOrderDate, Date endOrderDate) {
        Shop shop = shopService.getShopByUsername(username);

        Date startOfDay = startOfDay(startOrderDate);
        Date endOfDay = endOfDay(endOrderDate);

        List<Order> orders = orderRepository.findAllByShopIdAndOrderDateBetween(shop.getShopId(), startOfDay, endOfDay)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy đơn hàng nào!"));

        return listOrderResponse(orders, "Lấy danh sách đơn hàng trong khoảng thời gian thành công.", username);
    }


    @Override
    public ListOrderResponse getOrdersBetweenDateByStatus(String username, Date startOrderDate, Date endOrderDate, Status status) {
        Shop shop = shopService.getShopByUsername(username);

        Date startOfDay = startOfDay(startOrderDate);
        Date endOfDay = endOfDay(endOrderDate);

        List<Order> orders = orderRepository.findAllByShopIdAndOrderDateBetweenAndStatus(shop.getShopId(), startOfDay, endOfDay, status)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy đơn hàng nào!"));

        String message = orderService.messageByOrderStatus(status);

        return listOrderResponse(orders, message, username);
    }


    @Override
    public OrderResponse getOrderById(String username, Long orderId) {
        Shop shop = shopService.getShopByUsername(username);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy đơn hàng nào!"));

        if (!order.getShopId().equals(shop.getShopId())) {
            throw new NotFoundException("Không tìm thấy đơn hàng nào!");
        }

        return orderResponse(username, order, "Lấy đơn hàng thành công!", true);
    }


    @Override
    public OrderResponse updateStatusOrder(String username, Long orderId, Status status) {
        Shop shop = shopService.getShopByUsername(username);

        Order order = orderRepository.findById(orderId)
         .orElseThrow(() -> new NotFoundException("Không tìm thấy đơn hàng nào!"));
        if (!order.getShopId().equals(shop.getShopId())) {
            throw new NotFoundException("Không tìm thấy đơn hàng nào!");
        }
        checkStatus(order,status);

        order.setStatus(status);
        order.setUpdateAt(LocalDateTime.now());

        try {
            Order save = orderRepository.save(order);
            if (status.equals(Status.CANCEL)) {
                if (save.getVoucherOrders() != null) {
                    for (VoucherOrder voucherOrder : save.getVoucherOrders()) {
                        voucherOrderService.cancelVoucherOrder(voucherOrder.getVoucherOrderId());
                    }
                }
                List<OrderItem> orderItems = orderItemService.cancelOrderItem(save);
                save.setOrderItems(orderItems);
            }else {

                List<OrderItem> orderItems = orderItemShopService.updateStatusOrderItemByShop(save, status);
                save.setOrderItems(orderItems);
            }

            String message = messageUpdateStatusOrder(status);

            return orderResponse(username, save, message, false);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cập nhật trạng thái đơn hàng thất bại! " + e.getMessage());
        }

    }


    private String messageUpdateStatusOrder(Status status) {
        switch (status) {
            case CANCEL:
                return "Hủy đơn hàng thành công!";
            case COMPLETED:
                return "Giao đơn hàng thành công!";
            case RETURNED:
                return "Trả đơn hàng thành công!";
            case REFUNDED:
                return "Hoàn tiền đơn hàng thành công!";
            default:
                return "Cập nhật trạng thái đơn hàng thành công!";
        }
    }

private void checkStatus(Order order,Status status){

    if (order.getStatus().equals(Status.CANCEL)) {
        throw new IllegalArgumentException("Đơn hàng đã bị hủy!");
    }
    if (order.getStatus().equals(Status.COMPLETED)) {
        throw new IllegalArgumentException("Đơn hàng đã được giao!");
    }
    if (order.getStatus().equals(Status.RETURNED)) {
        throw new IllegalArgumentException("Đơn hàng đã được trả!");
    }
    if (order.getStatus().equals(Status.REFUNDED)) {
        throw new IllegalArgumentException("Đơn hàng đã được hoàn tiền!");
    }




}











    private Date startOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private Date endOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
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


    public ListOrderResponse listOrderResponse(List<Order> orders, String message, String username) {
        List<OrderDTO> orderDTOs = OrderDTO.convertListEntityToDTOs(orders);
        ListOrderResponse response = new ListOrderResponse();
        response.setOrderDTOs(orderDTOs);
        response.setUsername(username);
        response.setMessage(message);
        response.setStatus("ok");
        response.setCode(200);
        response.setCount(orders.size());
        return response;
    }


}
