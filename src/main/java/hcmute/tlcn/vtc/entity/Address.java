package hcmute.tlcn.vtc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hcmute.tlcn.vtc.entity.extra.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    private String province;

    private String district;

//    private String description;

    private String fullAddress;

    private String fullName;

    private String phone;

//    private String email;

//    private String note;

    private Status status;

    private LocalDateTime atCreate;

    private LocalDateTime atUpdate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;



}
