package hcmute.tlcn.vtc.model.dto.location_dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WardDTO {
    private String wardCode;
    private String name;
    private String nameEn;
    private String fullName;
    private String fullNameEn;
    private String codeName;
    private Integer administrativeUnitId;
    private String districtCode;
}
