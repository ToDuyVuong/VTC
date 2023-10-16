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
public class ProductVariant {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productVariantId;

    private String sku;

    private String image;

    private Long price;

    private Long quantity;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime atCreate;

    private LocalDateTime atUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_variant_attribute",
            joinColumns = @JoinColumn(name = "product_variant_id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "product_attribute_id", nullable = true)
    )
    private List<ProductAttribute> productAttributes;


}
