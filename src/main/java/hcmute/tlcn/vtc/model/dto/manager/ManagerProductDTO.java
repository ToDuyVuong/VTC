package hcmute.tlcn.vtc.model.dto.manager;


import hcmute.tlcn.vtc.model.entity.vtc.manager.ManagerProduct;

import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ManagerProductDTO {

    private Long managerProductId;
    private String note;
    private boolean isLock;
    private Long customerManagerId;
    private String usernameManager;
    private Long productId;
    private String productName;

    public static ManagerProductDTO convertEntityToDTO(ManagerProduct managerProduct){
        ManagerProductDTO managerProductDTO = new ManagerProductDTO();
        managerProductDTO.setManagerProductId(managerProduct.getManagerProductId());
        managerProductDTO.setNote(managerProduct.getNote());
        managerProductDTO.setLock(managerProduct.isLock());
        managerProductDTO.setCustomerManagerId(managerProduct.getManager().getCustomerId());
        managerProductDTO.setUsernameManager(managerProduct.getManager().getUsername());
        managerProductDTO.setProductId(managerProduct.getProduct().getProductId());
        managerProductDTO.setProductName(managerProduct.getProduct().getName());
        return managerProductDTO;
    }

    public static List<ManagerProductDTO> convertListEntitiesToListDTOs(List<ManagerProduct> managerProducts){
        List<ManagerProductDTO> managerProductDTOs = new ArrayList<>();
        for (ManagerProduct managerProduct : managerProducts){
            managerProductDTOs.add(convertEntityToDTO(managerProduct));
        }
        if (managerProductDTOs.isEmpty()){
            return Collections.emptyList();
        }
        managerProductDTOs.sort(Comparator.comparing(ManagerProductDTO::getProductName));

        return managerProductDTOs;
    }

}
