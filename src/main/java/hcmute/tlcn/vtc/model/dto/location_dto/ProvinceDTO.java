package hcmute.tlcn.vtc.model.dto.location_dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceDTO {

    private String provinceCode;
    private String name;
    private String nameEn;
    private String fullName;
    private String fullNameEn;
    private String codeName;
    private Integer administrativeRegionId;
    private Integer administrativeUnitId;


}
