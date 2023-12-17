package hcmute.tlcn.vtc.model.data.user.request;

import hcmute.tlcn.vtc.model.dto.AddressDTO;
import hcmute.tlcn.vtc.model.extra.Status;
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

//    private AddressDTO addressDTO;

    private String province;

    private String district;

    private String ward;

    private String fullAddress;

    private String fullName;

    private String phone;



    public void validate() {

        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Tài khoản không được để trống.");
        }

        if (province == null || province.isEmpty()) {
            throw new IllegalArgumentException("Tỉnh/Thành phố không được để trống.");
        }

        if (district == null || district.isEmpty()) {
            throw new IllegalArgumentException("Quận/Huyện không được để trống.");
        }

        if (ward == null || ward.isEmpty()) {
            throw new IllegalArgumentException("Phường/Xã không được để trống.");
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

        if (!Pattern.matches("[0-9]+", phone)) {
            throw new IllegalArgumentException("Số điện thoại chỉ được chứa ký tự số.");
        }

        if(phone.length() < 9 || phone.length() > 11){
            throw new IllegalArgumentException("Số điện thoại không hợp lệ.");
        }



//        if (addressDTO.getProvince() == null || addressDTO.getProvince().isEmpty()) {
//            throw new IllegalArgumentException("Tỉnh/Thành phố không được để trống.");
//        }
//
//        if (addressDTO.getDistrict() == null || addressDTO.getDistrict().isEmpty()) {
//            throw new IllegalArgumentException("Quận/Huyện không được để trống.");
//        }
//
//        if (addressDTO.getWard() == null || addressDTO.getWard().isEmpty()) {
//            throw new IllegalArgumentException("Phường/Xã không được để trống.");
//        }
//
//        if (addressDTO.getFullAddress() == null || addressDTO.getFullAddress().isEmpty()) {
//            throw new IllegalArgumentException("Địa chỉ không được để trống.");
//        }
//
//        if (addressDTO.getFullName() == null || addressDTO.getFullName().isEmpty()) {
//            throw new IllegalArgumentException("Họ tên không được để trống.");
//        }
//
//        if (addressDTO.getPhone() == null || addressDTO.getPhone().isEmpty()) {
//            throw new IllegalArgumentException("Số điện thoại không được để trống.");
//        }
//
//        if (!Pattern.matches("[0-9]+", addressDTO.getPhone())) {
//            throw new IllegalArgumentException("Số điện thoại chỉ được chứa ký tự số.");
//        }
//
//        if(addressDTO.getPhone().length() < 9 || addressDTO.getPhone().length() > 11){
//            throw new IllegalArgumentException("Số điện thoại không hợp lệ.");
//        }
//
//        if (addressDTO.getStatus() == null || Status.isValidStatus(String.valueOf(addressDTO.getStatus()))) {
//            throw new IllegalArgumentException("Trạng thái không hợp lệ.");
//        }

        trim();
    }

    public void trim() {
        this.username = this.username.trim();
        this.province = this.province.trim();
        this.district = this.district.trim();
        this.ward = this.ward.trim();
        this.fullAddress = this.fullAddress.trim();
        this.fullName = this.fullName.trim();
        this.phone = this.phone.trim();

    }


    public static AddressDTO convertRequestToDTO(AddressRequest addressRequest) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setProvince(addressRequest.getProvince());
        addressDTO.setDistrict(addressRequest.getDistrict());
        addressDTO.setWard(addressRequest.getWard());
        addressDTO.setFullAddress(addressRequest.getFullAddress());
        addressDTO.setFullName(addressRequest.getFullName());
        addressDTO.setPhone(addressRequest.getPhone());
        return addressDTO;
    }
}
