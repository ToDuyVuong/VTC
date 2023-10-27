package hcmute.tlcn.vtc.payment;

import hcmute.tlcn.vtc.model.dto.OrderDTO;
import hcmute.tlcn.vtc.model.entity.Order;

public class CreditCardPayment implements PaymentStrategy {
    @Override
    public void processPayment(OrderDTO orderDTO) {
        // Xử lý thanh toán bằng thẻ tín dụng
        System.out.println("Processing credit card payment...");
    }
}
