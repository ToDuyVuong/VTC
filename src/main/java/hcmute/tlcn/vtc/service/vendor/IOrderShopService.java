package hcmute.tlcn.vtc.service.vendor;

import hcmute.tlcn.vtc.model.data.paging.response.PageOrderResponse;
import hcmute.tlcn.vtc.model.data.user.response.ListOrderResponse;
import hcmute.tlcn.vtc.model.data.user.response.OrderResponse;
import hcmute.tlcn.vtc.model.extra.Status;

import java.util.Date;

public interface IOrderShopService {
    PageOrderResponse getPageOrder(String username, int page, int size);

    PageOrderResponse getPageOrderByStatus(String username, Status status, int page, int size);

    ListOrderResponse getOrders(String username);

    ListOrderResponse getOrdersByStatus(String username, Status status);

    ListOrderResponse getOrdersOnSameDay(String username, Date orderDate);

    ListOrderResponse getOrdersOnSameDayByStatus(String username, Date orderDate, Status status);

    ListOrderResponse getOrdersBetweenDate(String username, Date startOrderDate, Date endOrderDate);

    ListOrderResponse getOrdersBetweenDateByStatus(String username, Date startOrderDate, Date endOrderDate, Status status);

    OrderResponse getOrderById(String username, Long orderId);

    OrderResponse updateStatusOrder(String username, Long orderId, Status status);

    void checkRequestPageParams(int page, int size);
}
