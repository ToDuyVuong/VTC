package hcmute.tlcn.vtc.model.dto.vendor.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@RequiredArgsConstructor
public class ProductVariantRequest {

    private Long productVariantId;

    private String sku;

    private String image;

    private Long price;

    private Long quantity;

    private List<Long> attributeIds;

    public void validate() {
        if (this.sku == null || this.sku.isEmpty()) {
            throw new IllegalArgumentException("Mã sản phẩm không được để trống!");
        }

        if (this.price == null) {
            throw new IllegalArgumentException("Giá sản phẩm không được để trống!");
        }

        if (this.price < 0) {
            throw new IllegalArgumentException("Giá sản phẩm không được nhỏ hơn 0!");
        }

        if (this.quantity == null) {
            throw new IllegalArgumentException("Số lượng sản phẩm không được để trống!");
        }

        if (this.quantity < 0) {
            throw new IllegalArgumentException("Số lượng sản phẩm không được nhỏ hơn 0!");
        }
    }


    public void validateUpdate() {
        if (this.productVariantId == null) {
            throw new IllegalArgumentException("Mã sản phẩm không được để trống!");
        }

        validate();
    }






}
