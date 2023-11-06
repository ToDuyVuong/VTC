package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.data.user.request.CreateOrderUpdateRequest;
import hcmute.tlcn.vtc.model.data.user.response.OrderResponse;

import java.util.List;

public interface IOrderService {
    OrderResponse createOrder(String username, List<Long> cartIds);

    OrderResponse createOrderUpdate(CreateOrderUpdateRequest request);
}
