package hcmute.tlcn.vtc.entity;


import hcmute.tlcn.vtc.entity.extra.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Date;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String address;

    private String fullName;

    private String avatar;

    private Date birthday;

    @Enumerated(EnumType.STRING)
    private Role role;

    private OffsetDateTime atCreate;

    private OffsetDateTime atUpdate;


}
