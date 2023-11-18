package hcmute.tlcn.vtc.service.vendor;

import hcmute.tlcn.vtc.model.data.user.response.ListOrderResponse;
import hcmute.tlcn.vtc.model.extra.Status;

import java.util.Date;

public interface IOrderShopService {
    ListOrderResponse getOrders(String username);

    ListOrderResponse getOrdersByStatus(String username, Status status);

    ListOrderResponse getOrdersOnSameDay(String username, Date orderDate);

    ListOrderResponse getOrdersOnSameDayByStatus(String username, Date orderDate, Status status);

    ListOrderResponse getOrdersBetweenDate(String username, Date startOrderDate, Date endOrderDate);

    ListOrderResponse getOrdersBetweenDateByStatus(String username, Date startOrderDate, Date endOrderDate, Status status);
}
