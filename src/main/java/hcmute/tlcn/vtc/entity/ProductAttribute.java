package hcmute.tlcn.vtc.entity;

import hcmute.tlcn.vtc.entity.extra.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductAttribute {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productAttributeId;

    private String name;

    private String value;

    @Enumerated(EnumType.STRING)
    private Status status;

    private OffsetDateTime atCreate;

    private OffsetDateTime atUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_variant_id")
    private ProductVariant productVariant;






}
