package hcmute.tlcn.vtc.model.dto;

import hcmute.tlcn.vtc.model.entity.vtc.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderItemDTO {

    private Long orderItemId;

    private Long orderId;

    private Long cartId;

    private ProductVariantDTO productVariantDTO;

    private int quantity;

    private Long price;



    public static OrderItemDTO convertEntityToDTO(OrderItem orderItem){
        ProductVariantDTO productVariantDTO = ProductVariantDTO.convertEntityToDTO(orderItem.getCart().getProductVariant());
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        if (orderItem.getOrderItemId() != null){
            orderItemDTO.setOrderItemId(orderItem.getOrderItemId());
            orderItemDTO.setOrderId(orderItem.getOrder().getOrderId());
        }

        orderItemDTO.setCartId(orderItem.getCart().getCartId());
        orderItemDTO.setProductVariantDTO(productVariantDTO);
        orderItemDTO.setQuantity(orderItem.getCart().getQuantity());
        orderItemDTO.setPrice(orderItem.getCart().getProductVariant().getPrice());
        return orderItemDTO;
    }

    public static List<OrderItemDTO> convertListEntityToListDTO(List<OrderItem> orderItems){

        if (orderItems == null){
            return null;
        }
        List<OrderItemDTO> orderItemDTOs = new ArrayList<>();
        for (OrderItem orderItem : orderItems){
            OrderItemDTO orderItemDTO = convertEntityToDTO(orderItem);
            orderItemDTOs.add(orderItemDTO);
        }
        return orderItemDTOs;
    }

}
