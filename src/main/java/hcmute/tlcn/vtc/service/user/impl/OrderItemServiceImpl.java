package hcmute.tlcn.vtc.service.user.impl;

import hcmute.tlcn.vtc.model.entity.Cart;
import hcmute.tlcn.vtc.model.entity.OrderItem;
import hcmute.tlcn.vtc.repository.OrderItemRepository;
import hcmute.tlcn.vtc.service.user.ICartService;
import hcmute.tlcn.vtc.service.user.IOrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements IOrderItemService {

    @Autowired
    private final OrderItemRepository orderItemRepository;
    @Autowired
    private final ICartService cartService;


    public OrderItem createOrderItem(Long cartId, String username) {
        OrderItem orderItem = new OrderItem();
        Cart cart = cartService.getCartByUserNameAndId(username, cartId);
        orderItem.setOrder(null);
        orderItem.setCart(cart);
        return orderItem;
    }


    @Override
    public List<OrderItem> createOrderItems(String username, List<Long> cartIds) {
        List<OrderItem> orderItems = new ArrayList<>();

        for (Long cartId : cartIds) {
            orderItems.add(createOrderItem(cartId, username));
        }


        return orderItems;
    }


}
