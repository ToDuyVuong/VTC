package hcmute.tlcn.vtc.model.data.user.request;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {

    private String username;

    private Long productVariantId;

    private int quantity;

    public void validate() {

        if (productVariantId == null) {
            throw new IllegalArgumentException("Mã biến thể cửa sản phẩm không được để trống.");
        }

        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Tên đăng nhập không được để trống.");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Số lượng sản phẩm không hợp lệ.");
        }
    }

    public void validateUpdate() {

        if (productVariantId == null) {
            throw new IllegalArgumentException("Mã biến thể cửa sản phẩm không được để trống.");
        }

        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Tên đăng nhập không được để trống.");
        }
    }





}
