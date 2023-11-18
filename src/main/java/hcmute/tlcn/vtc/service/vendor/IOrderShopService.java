package hcmute.tlcn.vtc.service.vendor;

import hcmute.tlcn.vtc.model.data.user.response.ListOrderResponse;
import hcmute.tlcn.vtc.model.extra.Status;

public interface IOrderShopService {
    ListOrderResponse getOrders(String username);

    ListOrderResponse getOrdersByStatus(String username, Status status);
}
