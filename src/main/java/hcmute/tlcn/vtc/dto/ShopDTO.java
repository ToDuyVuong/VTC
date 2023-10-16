package hcmute.tlcn.vtc.dto;

import hcmute.tlcn.vtc.model.extra.Status;
import lombok.*;

import java.util.regex.Pattern;

@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class ShopDTO {

    private Long shopId;

    private String name;

    private String address;

    private String phone;

    private String email;

    private String avatar;

    private String description;

    private String openTime;

    private String closeTime;

    private Status status;

    private CustomerDTO customerDTO;

    public void validate() {
        if (this.name == null || this.name.isEmpty()) {
            throw new IllegalArgumentException("Tên cửa hàng không được để trống!");
        }

        if (this.address == null || this.address.isEmpty()) {
            throw new IllegalArgumentException("Địa chỉ cửa hàng không được để trống!");
        }

        if (this.phone == null || this.phone.isEmpty()) {
            throw new IllegalArgumentException("Số điện thoại cửa hàng không được để trống!");
        }

        if (this.email == null || this.email.isEmpty()) {
            throw new IllegalArgumentException("Email cửa hàng không được để trống!");
        }


        if (!Pattern.matches("[0-9]+", this.getPhone())) {
            throw new IllegalArgumentException("Số điện thoại chỉ được chứa ký tự số.");
        }

        if(this.getPhone().length() < 9 || this.getPhone().length() > 11){
            throw new IllegalArgumentException("Số điện thoại không hợp lệ.");
        }

        if (this.description == null || this.description.isEmpty()) {
            throw new IllegalArgumentException("Mô tả cửa hàng không được để trống!");
        }

        if (this.openTime == null || this.openTime.isEmpty()) {
            throw new IllegalArgumentException("Thời gian mở cửa không được để trống!");
        }

        if (this.closeTime == null || this.closeTime.isEmpty()) {
            throw new IllegalArgumentException("Thời gian đóng cửa không được để trống!");
        }

        if (this.status == null || !Status.isValidStatus(String.valueOf(this.status))) {
            throw new IllegalArgumentException("Trạng thái không hợp lệ!");
        }
    }
}
