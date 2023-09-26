package hcmute.tlcn.vtc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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

    @OneToMany(mappedBy = "brand")
    @JsonIgnore
    private List<Product> products;


}
