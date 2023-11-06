package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.entity.OrderItem;

import java.util.List;

public interface IOrderItemService {
    List<OrderItem> createOrderItems(String username, List<Long> cartIds);
}
