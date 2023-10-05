package hcmute.tlcn.vtc.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import hcmute.tlcn.vtc.entity.Customer;
import hcmute.tlcn.vtc.entity.extra.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Shop {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopId;

    private String name;

    private String address;

    private String phone;

    private String email;

    private String avatar;

    private String description;

    private String openTime;

    private String closeTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime atCreate;

    private LocalDateTime atUpdate;

    @OneToOne(fetch = FetchType.LAZY) // chỉ lấy dữ liệu khi gọi đến
    //    @JoinColumn(name = "customer_id", nullable = false, unique = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    @JsonIgnore  // sẻ không trả về trong trường này bằng Json trong hợp có truy vấn đến bảng này
    private Customer customer;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Category> categories;


}
