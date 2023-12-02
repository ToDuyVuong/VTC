package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.entity.vtc.Order;
import hcmute.tlcn.vtc.model.entity.vtc.OrderItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IOrderItemService {
    List<OrderItem> createOrderItems(String username, List<Long> cartIds);

    @Transactional
    List<OrderItem> saveOrderItem(Order order);

    @Transactional
    List<OrderItem> cancelOrderItem(Order order);
}
