package hcmute.tlcn.vtc.model.data.manager.response;

import hcmute.tlcn.vtc.model.dto.ManagerDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ManagerResponse extends ResponseAbstract {

    private ManagerDTO managerDTO;
}
