package hcmute.tlcn.vtc.dto;

import hcmute.tlcn.vtc.entity.extra.Role;
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

    private Date birthday;

    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

}
