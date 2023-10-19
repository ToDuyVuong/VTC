package hcmute.tlcn.vtc.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hcmute.tlcn.vtc.model.extra.Status;
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
public class Brand {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandId;

    private String name;

    private String image;

    private String description;

    private String information;

    private String origin;

    private Status status;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private boolean adminOnly;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "customer_id")
    // private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

//    @OneToMany(mappedBy = "brand")
//    @JsonIgnore
//    private List<Product> products;

}
