package hcmute.tlcn.vtc.model.dto;

import hcmute.tlcn.vtc.model.entity.vtc.manager.Manager;
import hcmute.tlcn.vtc.model.extra.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDTO {

    private Long managerId;
    private Status status;
    private Long customerId;
    private String username;


    public static ManagerDTO convertEntityToDTO(Manager manager) {
        ManagerDTO managerDTO = new ManagerDTO();
        managerDTO.setManagerId(manager.getManagerId());
        managerDTO.setStatus(manager.getStatus());
        managerDTO.setCustomerId(manager.getManager().getCustomerId());
        managerDTO.setUsername(manager.getManager().getUsername());
        return managerDTO;
    }

    public static List<ManagerDTO> convertEntitiesToDTOs(List<Manager> managers) {
        List<ManagerDTO> managerDTOs = new ArrayList<>();
        for (Manager manager : managers) {
            managerDTOs.add(convertEntityToDTO(manager));
        }
        return managerDTOs;
    }
}
