package hcmute.tlcn.vtc.model.data.manager.response;

import hcmute.tlcn.vtc.model.dto.manager.ManagerProductDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ManagerProductResponse extends ResponseAbstract {

    private ManagerProductDTO managerProductDTO;

}
