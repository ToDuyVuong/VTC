package hcmute.tlcn.vtc.model.dto.manager;

import hcmute.tlcn.vtc.model.entity.vtc.manager.ManagerShop;
import lombok.*;

import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ManagerShopDTO {

    private Long managerShopId;
    private String note;
    private boolean lock;
    private boolean delete;
    private String managerName;
    private String managerUsername;
    private Long shopId;
    private String shopName;


    public static ManagerShopDTO convertEntityToDTO(ManagerShop managerShop){
        ManagerShopDTO managerShopDTO = new ManagerShopDTO();
        managerShopDTO.setManagerShopId(managerShop.getManagerShopId());
        managerShopDTO.setNote(managerShop.getNote());
        managerShopDTO.setLock(managerShop.isLock());
        managerShopDTO.setDelete(managerShop.isDelete());
        managerShopDTO.setManagerName(managerShop.getManager().getFullName());
        managerShopDTO.setManagerUsername(managerShop.getManager().getUsername());
        managerShopDTO.setShopId(managerShop.getShop().getShopId());
        managerShopDTO.setShopName(managerShop.getShop().getName());

        return managerShopDTO;
    }


    public static List<ManagerShopDTO> convertEntitiesToDTOs(List<ManagerShop> managerShops){
        List<ManagerShopDTO> managerShopDTOs = new java.util.ArrayList<>();
        for(ManagerShop managerShop : managerShops){
            managerShopDTOs.add(convertEntityToDTO(managerShop));
        }
        managerShopDTOs.sort(Comparator.comparing(ManagerShopDTO::getShopName));

        return managerShopDTOs;
    }


}
