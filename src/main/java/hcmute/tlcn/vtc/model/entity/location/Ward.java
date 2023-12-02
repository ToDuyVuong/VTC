package hcmute.tlcn.vtc.model.entity.location;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wards")
public class Ward {

    @Id
    @Column(name = "code")
    private String wardCode;

    private String name;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "full_name_en")
    private String fullNameEn;

    @Column(name = "code_name")
    private String codeName;

    @ManyToOne
    @JoinColumn(name = "administrative_unit_id")
    private AdministrativeUnit administrativeUnit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_code")
    private District district;

    // Getters and setters
}