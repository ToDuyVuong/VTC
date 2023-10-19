package hcmute.tlcn.vtc.model.dto.vendor.request;

import hcmute.tlcn.vtc.model.extra.Status;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@RequiredArgsConstructor
public class ProductRequest {

    private Long productId;

    private String name;

    private String image;

    private String description;

    private String information;

    private Long categoryId;

    private Long brandId;

    private String username;

    private List<ProductVariantRequest> productVariantRequests;

    public void validate() {
        validateNotNullOrEmpty(name, "Tên sản phẩm không được để trống!");
        validateNotNullOrEmpty(image, "Hình ảnh sản phẩm không được để trống!");
        validateNotNullOrEmpty(description, "Mô tả sản phẩm không được để trống!");
        validateNotNullOrEmpty(information, "Thông tin sản phẩm không được để trống!");

        if (categoryId == null) {
            throw new IllegalArgumentException("Danh mục sản phẩm không được để trống!");
        }

        if (productVariantRequests == null || productVariantRequests.isEmpty()) {
            throw new IllegalArgumentException("Sản phẩm phải có ít nhất 1 biến thể!");
        }
    }

    public void validateUpdate() {
        if (productId == null) {
            throw new IllegalArgumentException("Mã sản phẩm không được để trống!");
        }

        validate();
    }

    private void validateNotNullOrEmpty(String field, String errorMessage) {
        if (field == null || field.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

}
