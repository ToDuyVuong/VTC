package hcmute.tlcn.vtc.model.data.location;

import hcmute.tlcn.vtc.model.dto.location_dto.WardDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WardResponse  extends ResponseAbstract {

    WardDTO wardDTO;
}
