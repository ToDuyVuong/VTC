package hcmute.tlcn.vtc.model.data.vendor.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@ToString
@RequiredArgsConstructor
public class ProductVariantRequest {

    private Long productVariantId;

    private String sku;

    private String image;

    private Long price;

    private int quantity;

    private List<Long> attributeIds;

    public void validate() {
        if (sku == null || sku.isEmpty()) {
            throw new IllegalArgumentException("Mã biến thể sản phẩm không được để trống!");
        }
        if (price == null) {
            throw new IllegalArgumentException("Giá sản phẩm không được để trống!");
        }
        if (quantity == 0) {
            throw new IllegalArgumentException("Số lượng sản phẩm không được để trống!");
        }

        if (price < 0) {
            throw new IllegalArgumentException("Giá sản phẩm không được nhỏ hơn 0!");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Số lượng sản phẩm không được nhỏ hơn 0!");
        }

        if (containsDuplicate(attributeIds)) {
            throw new IllegalArgumentException("Có mã thuộc tính trùng lặp trong danh sách thuộc tính!");
        }

        trim();
    }

    public void validateUpdate() {
        if (productVariantId == null) {
            throw new IllegalArgumentException("Mã biến thể sản phẩm không được để trống!");
        }
        validate();

    }


    private boolean containsDuplicate(List<Long> list) {
        Set<Long> set = new HashSet<>();
        for (Long item : list) {
            if (!set.add(item)) {
                return true;
            }
        }
        return false;
    }

    public void trim() {
        this.sku = this.sku.trim();
        this.image = this.image.trim();
    }
}