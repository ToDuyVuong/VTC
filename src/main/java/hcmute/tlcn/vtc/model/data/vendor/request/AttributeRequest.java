package hcmute.tlcn.vtc.model.data.vendor.request;

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

    private String username;

    public void validate() {
        if (this.name == null || this.name.isEmpty()) {
            throw new IllegalArgumentException("Tên thuộc tính không được để trống!");
        }

        if (this.value == null || this.value.isEmpty()) {
            throw new IllegalArgumentException("Giá trị thuộc tính không được để trống!");
        }

        if (this.username == null || this.username.isEmpty()) {
            throw new IllegalArgumentException("Tên đăng nhập không được để trống!");
        }
    }

    public void validateUpdate() {
        if (this.attributeId == null) {
            throw new IllegalArgumentException("Mã thuộc tính không được để trống!");
        }

        validate();
    }
}
