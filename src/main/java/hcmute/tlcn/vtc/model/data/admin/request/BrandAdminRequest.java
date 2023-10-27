package hcmute.tlcn.vtc.model.data.admin.request;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@RequiredArgsConstructor
public class BrandAdminRequest {

    private Long brandId;

    private String name;

    private String image;

    private String description;

    private String information;

    private String origin;

    private String username;

    public  void validate(){

        if (this.name == null || this.name.isEmpty()) {
            throw new IllegalArgumentException("Tên thương hiệu không được để trống!");
        }

        if (this.description == null || this.description.isEmpty()) {
            throw new IllegalArgumentException("Mô tả thương hiệu không được để trống!");
        }

        if (this.information == null || this.information.isEmpty()) {
            throw new IllegalArgumentException("Thông tin thương hiệu không được để trống!");
        }

        if (this.origin == null || this.origin.isEmpty()) {
            throw new IllegalArgumentException("Xuất xứ thương hiệu không được để trống!");
        }

        if (this.image == null || this.image.isEmpty()) {
            throw new IllegalArgumentException("Hình ảnh thương hiệu không được để trống!");
        }

        if (this.username == null || this.username.isEmpty()) {
            throw new IllegalArgumentException("Tên đăng nhập không được để trống!");
        }


    }

    public void checkBrandId(){
        if (this.brandId == null || this.brandId == 0) {
            throw new IllegalArgumentException("Mã thương hiệu không hợp lệ!");
        }
    }
}
