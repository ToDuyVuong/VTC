package hcmute.tlcn.vtc.model.data.user.request;

import hcmute.tlcn.vtc.model.extra.EmailValidator;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCustomerRequest {

    private String username;

    private String email;

    private String fullName;

//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date birthday;


    private boolean gender;

    public void validate() {

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email không được để trống.");
        } else if (!EmailValidator.isValidEmail(email)) {
            throw new IllegalArgumentException("Email không hợp lệ.");
        }

        if (fullName == null || fullName.isEmpty()) {
            throw new IllegalArgumentException("Tên đầy đủ không được để trống.");
        }

        if (birthday == null) {
            throw new IllegalArgumentException("Ngày sinh không được để trống.");
        }



    }
}
