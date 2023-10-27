package hcmute.tlcn.vtc.vnpay.payment;

import hcmute.tlcn.vtc.model.data.dto.OrderDTO;

public class CreditCardPayment implements PaymentStrategy {
    @Override
    public void processPayment(OrderDTO orderDTO) {
        // Xử lý thanh toán bằng thẻ tín dụng
        System.out.println("Processing credit card payment...");
    }
}
