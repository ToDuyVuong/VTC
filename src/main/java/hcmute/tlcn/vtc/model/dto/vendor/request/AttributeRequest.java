package hcmute.tlcn.vtc.model.dto.vendor.request;

import hcmute.tlcn.vtc.model.extra.Status;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@RequiredArgsConstructor
public class AttributeRequest {
    private Long attributeId;

    private String name;

    private String value;

    private Long shopId;

    public void validate() {
        if (this.name == null || this.name.isEmpty()) {
            throw new IllegalArgumentException("Tên thuộc tính không được để trống!");
        }

        if (this.value == null || this.value.isEmpty()) {
            throw new IllegalArgumentException("Giá trị thuộc tính không được để trống!");
        }

        if (this.shopId == null) {
            throw new IllegalArgumentException("Mã cửa hàng không được để trống!");
        }
    }

    public void validateUpdate() {
        if (this.attributeId == null) {
            throw new IllegalArgumentException("Mã thuộc tính không được để trống!");
        }

        validate();
    }
}
