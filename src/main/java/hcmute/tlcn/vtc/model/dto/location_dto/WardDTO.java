package hcmute.tlcn.vtc.model.dto.location_dto;

import hcmute.tlcn.vtc.model.entity.location.Ward;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WardDTO {
    private String wardCode;
    private String name;
//    private String nameEn;
//    private String fullName;
//    private String fullNameEn;
//    private String codeName;
//    private Integer administrativeUnitId;
//    private String districtCode;

    public static WardDTO convertEntityToDTO(Ward entity) {
        WardDTO dto = new WardDTO();
        dto.setWardCode(entity.getWardCode());
        dto.setName(entity.getName());
//        dto.setNameEn(entity.getNameEn());
//        dto.setFullName(entity.getFullName());
//        dto.setFullNameEn(entity.getFullNameEn());
//        dto.setCodeName(entity.getCodeName());
//        dto.setAdministrativeUnitId(entity.getAdministrativeUnit().getAdministrativeUnitId());
//        dto.setDistrictCode(entity.getDistrict().getDistrictCode());
        return dto;
    }

    public static List<WardDTO> convertEntitiesToDTOs(List<Ward> entities) {
        List<WardDTO> wardDTOs = new ArrayList<>();
        for (Ward entity : entities) {
            wardDTOs.add(convertEntityToDTO(entity));
        }
        wardDTOs.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        return wardDTOs;

//        return entities.stream().map(WardDTO::convertEntityToDTO).toList();
    }

}
