package hcmute.tlcn.vtc.dto;

import hcmute.tlcn.vtc.entity.extra.Status;
import lombok.*;

import java.util.regex.Pattern;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Long addressId;

    private String province;

    private String district;

    private String fullAddress;

    private String fullName;

    private String phone;

//    private String note;

    private Status status;

    public void validate () {
        if (province == null || province.isEmpty()) {
            throw new IllegalArgumentException("Tỉnh/Thành phố không được để trống.");
        }

        if (district == null || district.isEmpty()) {
            throw new IllegalArgumentException("Quận/Huyện không được để trống.");
        }

        if (fullAddress == null || fullAddress.isEmpty()) {
            throw new IllegalArgumentException("Địa chỉ không được để trống.");
        }

        if (fullName == null || fullName.isEmpty()) {
            throw new IllegalArgumentException("Họ tên không được để trống.");
        }

        if (phone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("Số điện thoại không được để trống.");
        }

        if(phone.length() < 9 || phone.length() > 11){
            throw new IllegalArgumentException("Số điện thoại không hợp lệ.");
        }

        if (!Pattern.matches("[0-9]+", phone)) {
            throw new IllegalArgumentException("Số điện thoại chỉ được chứa ký tự số.");
        }

        if (status == null) {
            throw new IllegalArgumentException("Trạng thái không được để trống.");
        }
    }


}
