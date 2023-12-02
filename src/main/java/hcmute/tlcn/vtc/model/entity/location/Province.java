package hcmute.tlcn.vtc.model.entity.location;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "provinces")
public class Province {

    @Id
    @Column(name = "code")
    private String provinceCode;

    private String name;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "full_name_en")
    private String fullNameEn;

    @Column(name = "code_name")
    private String codeName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "administrative_unit_id")
    private AdministrativeUnit administrativeUnit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "administrative_region_id")
    private AdministrativeRegion administrativeRegion;

    // Getters and setters
}
