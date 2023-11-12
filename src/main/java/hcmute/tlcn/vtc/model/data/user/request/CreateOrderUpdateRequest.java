package hcmute.tlcn.vtc.model.data.user.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderUpdateRequest {

    private String username;
    private List<Long> cartIds;
    private Long addressId;
    private Long voucherSystemId;
    private Long voucherShopId;
    private String note;
    private String paymentMethod;
    private String shippingMethod;

    public void validate() {
        if (cartIds == null || cartIds.isEmpty()) {
            throw new IllegalArgumentException("Giỏ hàng không được để trống!");
        }

//        if (paymentMethod == null || paymentMethod.isEmpty()) {
//            throw new IllegalArgumentException("Phương thức thanh toán không được để trống!");
//        }
//        if (!paymentMethod.equals("COD") ){
//            throw new IllegalArgumentException("Phương thức thanh toán không hợp lệ! Hiện tại chỉ hỗ trợ COD.");
//        }
//
//        if (shippingMethod == null || shippingMethod.isEmpty()) {
//            throw new IllegalArgumentException("Phương thức vận chuyển không được để trống!");
//        }
//        if (!shippingMethod.equals("GHN") && !shippingMethod.equals("GHTK") && !shippingMethod.equals("EXPRESS")) {
//            throw new IllegalArgumentException("Phương thức vận chuyển không hợp lệ! Hiện tại chỉ hỗ trợ GHN, GHTK và EXPRESS.");
//        }
    }

}
