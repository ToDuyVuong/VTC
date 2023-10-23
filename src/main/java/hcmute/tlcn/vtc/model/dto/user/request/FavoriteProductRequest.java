package hcmute.tlcn.vtc.model.dto.user.request;


import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteProductRequest {

    private Long favoriteProductId;

    private String username;

    private Long productId;

    public void validate() {
        if (productId == null) {
            throw new IllegalArgumentException("Mã sản phẩm không được để trống!");
        }

        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Tài khoản không được để trống!");
        }
    }

    public void validateUpdate() {
        if (favoriteProductId == null) {
            throw new IllegalArgumentException("Mã sản phẩm yêu thích không được để trống!");
        }
    }
}
