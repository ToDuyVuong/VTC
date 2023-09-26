package hcmute.tlcn.vtc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hcmute.tlcn.vtc.entity.extra.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private String content;

    private int rating;

    @Enumerated(EnumType.STRING)
    private Status status;

    private OffsetDateTime atCreate;

    private OffsetDateTime atUpdate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "review")
    @JsonIgnore
    private List<Comment> comments;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Order order;

}
