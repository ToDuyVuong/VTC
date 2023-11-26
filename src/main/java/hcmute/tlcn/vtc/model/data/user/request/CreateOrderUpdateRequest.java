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

        trim();
    }

    public void trim() {
        this.username = this.username.trim();
        this.note = this.note.trim();
        this.paymentMethod = this.paymentMethod.trim();
        this.shippingMethod = this.shippingMethod.trim();
    }

}
