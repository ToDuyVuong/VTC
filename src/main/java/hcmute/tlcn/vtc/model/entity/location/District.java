package hcmute.tlcn.vtc.model.entity.location;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "districts")
public class District {

    @Id
    @Column(name = "code")
    private String districtCode;

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
    @JoinColumn(name = "province_code")
    private Province province;

    // Getters and setters
}
