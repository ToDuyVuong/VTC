package hcmute.tlcn.vtc.service.vendor;

import hcmute.tlcn.vtc.model.entity.vtc.Order;
import hcmute.tlcn.vtc.model.entity.vtc.OrderItem;
import hcmute.tlcn.vtc.model.extra.Status;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IOrderItemShopService {
    @Transactional
    List<OrderItem> updateStatusOrderItemByShop(Order order, Status status);
}
