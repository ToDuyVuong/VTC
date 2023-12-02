package hcmute.tlcn.vtc.model.dto.location_dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdministrativeRegionDTO {
    private Integer administrativeRegionId;
    private String name;
    private String nameEn;
    private String codeName;
    private String codeNameEn;
}
