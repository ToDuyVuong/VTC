package hcmute.tlcn.vtc.model.data.vendor.request;

import hcmute.tlcn.vtc.model.extra.EmailValidator;
import hcmute.tlcn.vtc.model.extra.Status;
import lombok.*;

import java.util.regex.Pattern;

@Data
@ToString
@RequiredArgsConstructor
public class RegisterShopRequest {
    private String name;

    private String address;

    private String province;

    private String district;

    private String ward;

    private String phone;

    private String email;

    private String avatar;

    private String description;

    private String openTime;

    private String closeTime;

    private String username;

    public void validate() {
        if (this.name == null || this.name.isEmpty()) {
            throw new IllegalArgumentException("Tên cửa hàng không được để trống!");
        }

        if (this.address == null || this.address.isEmpty()) {
            throw new IllegalArgumentException("Địa chỉ cửa hàng không được để trống!");
        }

        if (this.province == null || this.province.isEmpty()) {
            throw new IllegalArgumentException("Tỉnh/Thành phố không được để trống!");
        }

        if (this.district == null || this.district.isEmpty()) {
            throw new IllegalArgumentException("Quận/Huyện không được để trống!");
        }

        if (this.ward == null || this.ward.isEmpty()) {
            throw new IllegalArgumentException("Phường/Xã không được để trống!");
        }

        if (this.phone == null || this.phone.isEmpty()) {
            throw new IllegalArgumentException("Số điện thoại cửa hàng không được để trống!");
        }

        if (!Pattern.matches("[0-9]+", this.getPhone())) {
            throw new IllegalArgumentException("Số điện thoại chỉ được chứa ký tự số.");
        }

        if (this.email == null || this.email.isEmpty()) {
            throw new IllegalArgumentException("Email cửa hàng không được để trống!");
        }

        if (!EmailValidator.isValidEmail(email)) {
            throw new IllegalArgumentException("Email không hợp lệ.");
        }

        if (this.openTime == null || this.openTime.isEmpty()) {
            throw new IllegalArgumentException("Giờ mở cửa không được để trống!");
        }

        if (this.closeTime == null || this.closeTime.isEmpty()) {
            throw new IllegalArgumentException("Giờ đóng cửa không được để trống!");
        }

        if (this.description == null || this.description.isEmpty()) {
            throw new IllegalArgumentException("Mô tả cửa hàng không được để trống!");
        }

        if (this.avatar == null || this.avatar.isEmpty()) {
            throw new IllegalArgumentException("Ảnh đại diện không được để trống!");
        }

//        if (this.status == null || Status.isValidStatus(String.valueOf(this.status))) {
//            throw new IllegalArgumentException("Trạng thái không hợp lệ!");
//        }

        if (!Pattern.matches("[0-9]+", this.getPhone())) {
            throw new IllegalArgumentException("Số điện thoại chỉ được chứa ký tự số.");
        }

        if (this.getPhone().length() < 9 || this.getPhone().length() > 11) {
            throw new IllegalArgumentException("Số điện thoại không hợp lệ.");
        }

        trim();
    }

    public void trim() {
        this.name = this.name.trim();
        this.address = this.address.trim();
        this.phone = this.phone.trim();
        this.email = this.email.trim();
        this.avatar = this.avatar.trim();
        this.description = this.description.trim();
        this.openTime = this.openTime.trim();
        this.closeTime = this.closeTime.trim();
        this.username = this.username.trim();
    }

}
