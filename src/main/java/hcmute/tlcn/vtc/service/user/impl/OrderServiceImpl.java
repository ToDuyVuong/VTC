package hcmute.tlcn.vtc.service.user.impl;

import hcmute.tlcn.vtc.model.data.user.response.OrderResponse;
import hcmute.tlcn.vtc.model.dto.OrderDTO;
import hcmute.tlcn.vtc.model.entity.Order;
import hcmute.tlcn.vtc.repository.OrderRepository;
import hcmute.tlcn.vtc.service.user.ICartService;
import hcmute.tlcn.vtc.service.user.IOrderItemService;
import hcmute.tlcn.vtc.service.user.IOrderService;
import hcmute.tlcn.vtc.service.user.IVoucherOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final IOrderItemService orderItemService;
    @Autowired
    private final IVoucherOrderService voucherOrderService;
    @Autowired
    private final ICartService cartService;


    public OrderResponse createOrder(String username, List<Long> carts){
        return null;
    }




    private OrderResponse orderResponse(String username, Order order, String message, boolean created){

        OrderDTO orderDTO = new OrderDTO();

        OrderResponse response = new OrderResponse();
        response.setUsername(username);

        return null;
    }



}
