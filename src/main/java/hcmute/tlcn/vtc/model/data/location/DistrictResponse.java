package hcmute.tlcn.vtc.model.data.location;

import hcmute.tlcn.vtc.model.dto.location_dto.DistrictDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DistrictResponse  extends ResponseAbstract {

    private DistrictDTO districtDTO;
}
