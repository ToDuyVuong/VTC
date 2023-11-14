package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.entity.Order;
import hcmute.tlcn.vtc.model.entity.OrderItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IOrderItemService {
    List<OrderItem> createOrderItems(String username, List<Long> cartIds);

    @Transactional
    List<OrderItem> saveOrderItem(Order order);
}
