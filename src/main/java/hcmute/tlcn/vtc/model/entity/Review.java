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
public class Review {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private String content;

    private int rating;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

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
