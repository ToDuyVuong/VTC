package hcmute.tlcn.vtc.model.dto.vendor.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@RequiredArgsConstructor
public class CategoryShopRequest {

    private Long categoryId;
    private String name;
    private String description;
    private String image;
    private Long shopId;
    private Long parentId;

    public void validate() {
        if (this.name == null || this.name.isEmpty()) {
            throw new IllegalArgumentException("Tên danh mục không được để trống!");
        }

        if (this.description == null || this.description.isEmpty()) {
            throw new IllegalArgumentException("Mô tả danh mục không được để trống!");
        }

        if (this.image == null || this.image.isEmpty()) {
            throw new IllegalArgumentException("Hình ảnh danh mục không được để trống!");
        }

        if (this.shopId == null) {
            throw new IllegalArgumentException("Mã cửa hàng không được để trống!");
        }

        if (this.parentId == null) {
            throw new IllegalArgumentException("Mã danh mục cha không được để trống!");
        }
    }


    public void validateUpdate() {
        if (this.categoryId == null) {
            throw new IllegalArgumentException("Mã danh mục không được để trống!");
        }

        validate();
    }


}
