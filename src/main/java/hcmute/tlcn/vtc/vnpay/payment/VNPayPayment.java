package hcmute.tlcn.vtc.vnpay.payment;

import hcmute.tlcn.vtc.model.dto.OrderDTO;

public class VNPayPayment implements PaymentStrategy{

    private String vnpayApiEndpoint;
    private String merchantId;
    private String merchantHash;

    public VNPayPayment(String vnpayApiEndpoint, String merchantId, String merchantHash) {
        this.vnpayApiEndpoint = vnpayApiEndpoint;
        this.merchantId = merchantId;
        this.merchantHash = merchantHash;
    }

    @Override
    public void processPayment(OrderDTO order) {
        // Xử lý thanh toán bằng PayPal
//        VnPayRequest vnPayRequest = new VnPayRequest();
//        vnPayRequest.setVnp_Amount(order.getAmount()); // Số tiền thanh toán (đơn vị là VNĐ)
//        vnPayRequest.setVnp_Command("pay");
//        vnPayRequest.setVnp_CreateDate(new SimpleDateFormat("yyyyMMddHHmmss").format(LocalDateTime.now()));
//        vnPayRequest.setVnp_CurrCode("VND");
//        vnPayRequest.setVnp_IpAddr(order.getCustomer().getIpAddress());
//        vnPayRequest.setVnp_Locale("vn");
//        vnPayRequest.setVnp_OrderInfo(order.getOrderInfo());
//        vnPayRequest.setVnp_OrderType("topup");
//        vnPayRequest.setVnp_ReturnUrl(order.getReturnUrl()); // URL để VNPay gửi kết quả thanh toán
//        vnPayRequest.setVnp_TmnCode(merchantId);
//        vnPayRequest.setVnp_TxnRef(System.currentTimeMillis() + "");
//
//        // Tính mã hash và thêm vào request
//        String hashData = vnPayRequest.createHashData(merchantHash);
//        vnPayRequest.setVnp_SecureHash(hashData);
//
//        // Redirect người dùng đến trang thanh toán VNPay
//        String redirectUrl = vnpayApiEndpoint + "?" + vnPayRequest.getVnp_Url();
//
//        return redirectUrl;
    }
}
