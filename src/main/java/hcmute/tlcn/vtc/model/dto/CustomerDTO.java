package hcmute.tlcn.vtc.model.dto;

import hcmute.tlcn.vtc.model.extra.EmailValidator;
import hcmute.tlcn.vtc.model.extra.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Long customerId;

    private String username;

    private String email;

    private boolean gender;

    private String fullName;

//    private String phone;

    private Date birthday;

    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


    public void validate() {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Tài khoản không được để trống.");
        }

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

//        if (phone == null || phone.isEmpty()) {
//            throw new IllegalArgumentException("Số điện thoại không được để trống.");
//        }


    }

}
