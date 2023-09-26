package hcmute.tlcn.vtc.entity;

import hcmute.tlcn.vtc.entity.extra.Status;
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
public class Voucher {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voucherId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String code;

    private String name;

    private String description;

    private Long discount;

    private Long minPrice;

    private Long maxPrice;

    private Long maxDiscount;

    private Long quantity;

    private Date startDate;

    private Date endDate;

    private Long quantityUsed;

    private OffsetDateTime atCreate;

    private OffsetDateTime atUpdate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "voucher_order",
            joinColumns = @JoinColumn(name = "voucher_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<Order> orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;




}
