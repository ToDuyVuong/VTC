package hcmute.tlcn.vtc.model.dto.user.request;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {

    private Long cartId;

    private String username;

    private Long productVariantId;

    private Long quantity;

    public void validate() {

        if (productVariantId == null) {
            throw new IllegalArgumentException("Mã biến thể cửa sản phẩm không được để trống.");
        }

        if (quantity == null) {
            throw new IllegalArgumentException("Số lượng không được để trống.");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Số lượng không hợp lệ.");
        }
    }

    public void validateUpdate() {

        if (cartId == null) {
            throw new IllegalArgumentException("Mã giỏ hàng không được để trống.");
        }

        validate();
    }

}
