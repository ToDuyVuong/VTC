package hcmute.tlcn.vtc.service.vendor.impl;

import hcmute.tlcn.vtc.model.data.user.response.ListOrderResponse;
import hcmute.tlcn.vtc.model.data.user.response.OrderResponse;
import hcmute.tlcn.vtc.model.dto.OrderDTO;
import hcmute.tlcn.vtc.model.entity.Order;
import hcmute.tlcn.vtc.model.entity.Shop;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.repository.CartRepository;
import hcmute.tlcn.vtc.repository.OrderItemRepository;
import hcmute.tlcn.vtc.repository.OrderRepository;
import hcmute.tlcn.vtc.service.admin.IVoucherAdminService;
import hcmute.tlcn.vtc.service.user.*;
import hcmute.tlcn.vtc.service.vendor.IOrderShopService;
import hcmute.tlcn.vtc.service.vendor.IShopService;
import hcmute.tlcn.vtc.service.vendor.IVoucherShopService;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderShopServiceImpl implements IOrderShopService {

    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final IOrderItemService orderItemService;
    @Autowired
    private final IVoucherOrderService voucherOrderService;
    @Autowired
    private final ICartService cartService;
    @Autowired
    private final ICustomerService customerService;
    @Autowired
    private final IAddressService addressService;
    @Autowired
    private final IVoucherShopService voucherShopService;
    @Autowired
    private final IVoucherAdminService voucherSystemService;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private final IShopService shopService;
    @Autowired
    private final IOrderService orderService;


    @Override
    public ListOrderResponse getOrders(String username) {
        Shop shop = shopService.getShopByUsername(username);

        List<Order> orders = orderRepository.findAllByShopId(shop.getShopId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy đơn hàng nào!"));

        return listOrderResponse(orders, "Lấy danh sách đơn hàng thành công!", username);
    }


    @Override
    public ListOrderResponse getOrdersByStatus(String username, Status status) {
        Shop shop = shopService.getShopByUsername(username);

        List<Order> orders = orderRepository.findAllByShopIdAndStatus(shop.getShopId(), status)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy đơn hàng nào!"));

        String message = orderService.messageByOrderStatus(status);

        return listOrderResponse(orders, message, username);
    }



    private OrderResponse orderResponse(String username, Order order, String message, boolean created) {

        OrderDTO orderDTO = OrderDTO.convertEntityToDTOCreate(order);

        OrderResponse response = new OrderResponse();
        response.setUsername(username);
        response.setOrderDTO(orderDTO);
        response.setMessage(message);
        response.setStatus(created ? "ok" : "success");
        response.setCode(200);
        return response;
    }


    public ListOrderResponse listOrderResponse(List<Order> orders, String message, String username) {
        List<OrderDTO> orderDTOs = OrderDTO.convertListEntityToDTOs(orders);
        ListOrderResponse response = new ListOrderResponse();
        response.setOrderDTOs(orderDTOs);
        response.setUsername(username);
        response.setMessage(message);
        response.setStatus("ok");
        response.setCode(200);
        response.setCount(orders.size());
        return response;
    }


}
