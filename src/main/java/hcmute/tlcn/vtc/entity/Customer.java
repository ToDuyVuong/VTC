package hcmute.tlcn.vtc.entity;


import hcmute.tlcn.vtc.entity.extra.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String username;

    private String password;

    private String email;

    private boolean gender;

    private String fullName;

    private Date birthday;

    @Enumerated(EnumType.STRING)
    private Role role;

    private OffsetDateTime atCreate;

    private OffsetDateTime atUpdate;

//    @OneToOne(cascade = CascadeType.ALL) // khi thêm 1 customer thì sẻ thêm 1 address
//    @JoinColumn(name = "address_id")
//    @JsonManagedReference
//    private Address address;


}
