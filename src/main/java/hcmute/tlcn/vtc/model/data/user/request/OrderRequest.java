package hcmute.tlcn.vtc.model.data.user.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private String username;

    private List<Long> cartIds;

    private Long addressId;

    private List<Long> voucherIds;

    private String paymentMethod;

    private String shippingMethod;

    private String note;

    public static void validate(OrderRequest request) {

        if (request.getCartIds() == null || request.getCartIds().isEmpty()) {
            throw new IllegalArgumentException("Danh sách sản phẩm không được để trống!");
        }

        if (request.getAddressId() == null) {
            throw new IllegalArgumentException("Mã địa chỉ không được để trống!");
        }

        if (request.getPaymentMethod() == null || request.getPaymentMethod().isEmpty()) {
            throw new IllegalArgumentException("Phương thức thanh toán không được để trống! ");
        }
        if (!request.getPaymentMethod().equals("COD")) {
            throw new IllegalArgumentException("Phương thanh toán không hợp lệ! Hiện tai chỉ hỗ trợ COD");
        }

        if (request.getShippingMethod() == null || request.getShippingMethod().isEmpty()) {
            throw new IllegalArgumentException("Phương thức vận chuyển không được để trống.");
        }
        if (!request.getShippingMethod().equals("GHN") &&
                !request.getShippingMethod().equals("GHTK") &&
                !request.getShippingMethod().equals("EXPRESS")) {
            throw new IllegalArgumentException("Phương thức vận chuyển không hợp lệ! Hiện tại chỉ hỗ trợ GHN, GHTK, EXPRESS");
        }

        request.trim();
    }

    public void trim() {
        this.username = this.username.trim();
        this.note = this.note.trim();
        this.paymentMethod = this.paymentMethod.trim();
        this.shippingMethod = this.shippingMethod.trim();
    }


}
