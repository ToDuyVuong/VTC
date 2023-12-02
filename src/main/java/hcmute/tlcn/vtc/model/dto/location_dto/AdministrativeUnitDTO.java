package hcmute.tlcn.vtc.model.dto.location_dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdministrativeUnitDTO {
    private Integer administrativeUnitId;
    private String fullName;
    private String fullNameEn;
    private String shortName;
    private String shortNameEn;
    private String codeName;
    private String codeNameEn;
}
