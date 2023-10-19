package hcmute.tlcn.vtc.model.dto.vendor.request;

import hcmute.tlcn.vtc.model.extra.Status;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

        Set<String> skuSet = productVariantRequests
                .stream()
                .map(ProductVariantRequest::getSku)
                .collect(Collectors.toSet());
        if (skuSet.size() < productVariantRequests.size()) {
            throw new IllegalArgumentException("Có mã biến thể sản phẩm trùng lặp trong danh sách biến thể của sản phẩm!");
        }

        if (productVariantRequests == null || productVariantRequests.isEmpty()) {
            throw new IllegalArgumentException("Sản phẩm phải có ít nhất 1 biến thể!");
        } else {
            for (ProductVariantRequest variantRequest : productVariantRequests) {
                variantRequest.validate();
            }
        }
    }

    public void validateUpdate() {
        if (productId == null) {
            throw new IllegalArgumentException("Mã sản phẩm không được để trống!");
        }

        validate();

        for (ProductVariantRequest variantRequest : productVariantRequests) {
            variantRequest.validateUpdate();
        }
    }

    private void validateNotNullOrEmpty(String field, String errorMessage) {
        if (field == null || field.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

}
