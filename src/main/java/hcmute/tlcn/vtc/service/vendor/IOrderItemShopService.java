package hcmute.tlcn.vtc.service.vendor;

import hcmute.tlcn.vtc.model.entity.Order;
import hcmute.tlcn.vtc.model.entity.OrderItem;
import hcmute.tlcn.vtc.model.extra.Status;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IOrderItemShopService {
    @Transactional
    List<OrderItem> updateStatusOrderItemByShop(Order order, Status status);
}
