package hcmute.tlcn.vtc.model.extra.entity;

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
public class Product {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;

    private String image;

    private String description;

    private String information;

    private Long sold;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime atCreate;

    private LocalDateTime atUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "product")
    private List<ProductVariant> productVariants;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;





}