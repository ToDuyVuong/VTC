package hcmute.tlcn.vtc.service.user.impl;

import hcmute.tlcn.vtc.model.data.user.response.OrderResponse;
import hcmute.tlcn.vtc.model.dto.OrderDTO;
import hcmute.tlcn.vtc.model.dto.OrderItemDTO;
import hcmute.tlcn.vtc.model.entity.*;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.repository.OrderRepository;
import hcmute.tlcn.vtc.service.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public OrderResponse createOrder(String username, List<Long> cartIds) {
       Order order = createTemporaryOrder(username, cartIds);
        return orderResponse(username, order, "Tạo đơn hàng mới thành công.", true);
    }

    private Order createTemporaryOrder(String username, List<Long> cartIds) {
        Customer customer = customerService.getCustomerByUsername(username);
        List<OrderItem> orderItems = orderItemService.createOrderItems(username, cartIds);
        Address address = addressService.getAddressActiveByUsername(username);

        Long totalPrice = getTotalPrice(orderItems);
        Long discount = 0L;

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderItems(orderItems);
        order.setAddress(address);
        order.setStatus(Status.CART);
        order.setTotalPrice(totalPrice);
        order.setVoucherOrders(null);
        order.setPaymentMethod("COD");
        order.setShippingMethod("GHN");
        order.setPaymentTotal(order.getTotalPrice());
        order.setDiscount(discount);
        order.setPaymentTotal(totalPrice - discount);
        order.setNote(null);
        order.setOrderDate(new Date());
        order.setOrderItems(orderItems);
        order.setCount(getTotalCount(orderItems));
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


    private Long getTotalPrice (List<OrderItem> orderItems) {
        long totalPrice = 0L;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getCart().getProductVariant().getPrice() * orderItem.getCart().getQuantity();
        }
        return totalPrice;
    }

    private int getTotalCount (List<OrderItem> orderItems) {
        int count = 0;
        for (OrderItem orderItem : orderItems) {
            count += orderItem.getCart().getQuantity();
        }
        return count;
    }


}
