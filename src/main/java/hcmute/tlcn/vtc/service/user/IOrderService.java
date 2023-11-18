package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.data.user.request.CreateOrderUpdateRequest;
import hcmute.tlcn.vtc.model.data.user.response.ListOrderResponse;
import hcmute.tlcn.vtc.model.data.user.response.OrderResponse;
import hcmute.tlcn.vtc.model.extra.Status;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IOrderService {
    OrderResponse createOrder(String username, List<Long> cartIds);

    OrderResponse createOrderUpdate(CreateOrderUpdateRequest request);

    @Transactional
    OrderResponse saveOrder(CreateOrderUpdateRequest request);

    ListOrderResponse getOrders(String username);

    ListOrderResponse getOrdersByStatus(String username, Status status);

    OrderResponse getOrderDetail(String username, Long orderId);

    @Transactional
    OrderResponse cancelOrder(String username, Long orderId);

    String messageByOrderStatus(Status status);
}
