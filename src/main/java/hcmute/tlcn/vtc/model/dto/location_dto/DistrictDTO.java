package hcmute.tlcn.vtc.model.dto.location_dto;

import hcmute.tlcn.vtc.model.entity.location.District;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DistrictDTO {
    private String districtCode;
    private String name;
//    private String nameEn;
//    private String fullName;
//    private String fullNameEn;
//    private String codeName;
//    private Integer administrativeUnitId;
//    private String provinceCode;


    public static DistrictDTO convertEntityToDTO(District entity) {
        DistrictDTO dto = new DistrictDTO();
        dto.setDistrictCode(entity.getDistrictCode());
        dto.setName(entity.getName());
//        dto.setNameEn(entity.getNameEn());
//        dto.setFullName(entity.getFullName());
//        dto.setFullNameEn(entity.getFullNameEn());
//        dto.setCodeName(entity.getCodeName());
//        dto.setAdministrativeUnitId(entity.getAdministrativeUnit().getAdministrativeUnitId());
//        dto.setProvinceCode(entity.getProvince().getProvinceCode());
        return dto;
    }

    public static List<DistrictDTO> convertEntitiesToDTOs(List<District> entities) {
        List<DistrictDTO> districtDTOs = new ArrayList<>();
        for (District entity : entities) {
            districtDTOs.add(convertEntityToDTO(entity));
        }
        districtDTOs.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        return districtDTOs;
//        return entities.stream().map(DistrictDTO::convertEntityToDTO).toList();
    }

}
