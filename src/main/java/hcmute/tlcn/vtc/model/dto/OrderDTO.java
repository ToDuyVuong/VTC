package hcmute.tlcn.vtc.model.dto;


import hcmute.tlcn.vtc.model.entity.Order;
import hcmute.tlcn.vtc.model.extra.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderDTO {

    private Long orderId;

    private String note;

    private String paymentMethod;

    private String shippingMethod;

    private int count;

    private Long shopId;

    private String shopName;

    private Long totalPrice;

    private Long discount;

    private Long shippingFee;

    private Long paymentTotal;

    private Status status;

    private AddressDTO addressDTO;

    private List<VoucherOrderDTO> voucherOrderDTOs;

    private List<OrderItemDTO> orderItemDTOs;

    private Date orderDate;


    public static OrderDTO convertEntityToDTOCreate(Order order) {
        OrderDTO orderDTO = new OrderDTO();
//        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setNote(order.getNote());
        orderDTO.setPaymentMethod(order.getPaymentMethod());
        orderDTO.setShippingMethod(order.getShippingMethod());
        orderDTO.setCount(order.getCount());
        orderDTO.setShopId(order.getShopId());
        orderDTO.setShopName(order.getShopName());
        orderDTO.setDiscount(order.getDiscount());
        orderDTO.setPaymentTotal(order.getPaymentTotal());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setShippingFee(order.getShippingFee());

        orderDTO.setOrderItemDTOs(OrderItemDTO.convertListEntityToListDTO(order.getOrderItems()));
        orderDTO.setAddressDTO(AddressDTO.convertEntityToDTO(order.getAddress()));
        orderDTO.setVoucherOrderDTOs(VoucherOrderDTO.convertListEntityToListDTO(order.getVoucherOrders()));

        return orderDTO;
    }


}
