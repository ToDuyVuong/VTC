package hcmute.tlcn.vtc.model.data.user.request;

import hcmute.tlcn.vtc.model.extra.Status;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressStatusRequest {

    private String username;

    private String addressId;

    private Status status;

    public void validate() {

        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Tài khoản không được để trống.");
        }

        if (addressId == null || addressId.isEmpty()) {
            throw new IllegalArgumentException("Địa chỉ không được để trống.");
        }

        if (status == null) {
            throw new IllegalArgumentException("Trạng thái không được để trống.");
        }

        if (Status.isValidStatus(String.valueOf(status))) {
            throw new IllegalArgumentException("Trạng thái không hợp lệ.");
        }

        trim();
    }

    public void trim() {
        this.username = this.username.trim();
        this.addressId = this.addressId.trim();
    }
}
