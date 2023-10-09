package hcmute.tlcn.vtc.dto.user.request;

import hcmute.tlcn.vtc.dto.AddressDTO;
import hcmute.tlcn.vtc.entity.extra.Status;
import lombok.*;

import java.util.regex.Pattern;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    private String username;

    private AddressDTO addressDTO;

    public void validate() {

        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Tài khoản không được để trống.");
        }

        if (addressDTO.getProvince() == null || addressDTO.getProvince().isEmpty()) {
            throw new IllegalArgumentException("Tỉnh/Thành phố không được để trống.");
        }

        if (addressDTO.getDistrict() == null || addressDTO.getDistrict().isEmpty()) {
            throw new IllegalArgumentException("Quận/Huyện không được để trống.");
        }

        if (addressDTO.getFullAddress() == null || addressDTO.getFullAddress().isEmpty()) {
            throw new IllegalArgumentException("Địa chỉ không được để trống.");
        }

        if (addressDTO.getFullName() == null || addressDTO.getFullName().isEmpty()) {
            throw new IllegalArgumentException("Họ tên không được để trống.");
        }

        if (addressDTO.getPhone() == null || addressDTO.getPhone().isEmpty()) {
            throw new IllegalArgumentException("Số điện thoại không được để trống.");
        }

        if (!Pattern.matches("[0-9]+", addressDTO.getPhone())) {
            throw new IllegalArgumentException("Số điện thoại chỉ được chứa ký tự số.");
        }

        if(addressDTO.getPhone().length() < 9 || addressDTO.getPhone().length() > 11){
            throw new IllegalArgumentException("Số điện thoại không hợp lệ.");
        }

        if (addressDTO.getStatus() == null || !Status.isValidStatus(String.valueOf(addressDTO.getStatus()))) {
            throw new IllegalArgumentException("Trạng thái không hợp lệ.");
        }
    }
}
