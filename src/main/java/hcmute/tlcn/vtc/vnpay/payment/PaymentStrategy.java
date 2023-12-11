package hcmute.tlcn.vtc.vnpay.payment;

import hcmute.tlcn.vtc.model.dto.OrderDTO;

public interface PaymentStrategy {
    void processPayment(OrderDTO orderDTO);
}
