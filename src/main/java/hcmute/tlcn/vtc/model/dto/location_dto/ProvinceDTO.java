package hcmute.tlcn.vtc.model.dto.location_dto;

import hcmute.tlcn.vtc.model.entity.location.Province;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceDTO {

    private String provinceCode;
    private String name;
//    private String nameEn;
//    private String fullName;
//    private String fullNameEn;
//    private String codeName;
//    private Integer administrativeRegionId;
//    private Integer administrativeUnitId;

    public static ProvinceDTO convertEntityToDTO(Province entity) {
        ProvinceDTO dto = new ProvinceDTO();
        dto.setProvinceCode(entity.getProvinceCode());
        dto.setName(entity.getName());
//        dto.setNameEn(entity.getNameEn());
//        dto.setFullName(entity.getFullName());
//        dto.setFullNameEn(entity.getFullNameEn());
//        dto.setCodeName(entity.getCodeName());
//        dto.setAdministrativeRegionId(entity.getAdministrativeRegion().getAdministrativeRegionId());
//        dto.setAdministrativeUnitId(entity.getAdministrativeUnit().getAdministrativeUnitId());
        return dto;
    }

    public static List<ProvinceDTO> convertEntitiesToDTOs(List<Province> entities) {

        List<ProvinceDTO> provinceDTOs = new ArrayList<>();
        for (Province entity : entities) {
            provinceDTOs.add(convertEntityToDTO(entity));
        }
        provinceDTOs.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));

        return provinceDTOs;
//        return entities.stream().map(ProvinceDTO::convertEntityToDTO).toList();
    }

}
