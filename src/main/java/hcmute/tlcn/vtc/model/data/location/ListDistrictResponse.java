package hcmute.tlcn.vtc.model.data.location;

import hcmute.tlcn.vtc.model.dto.location_dto.DistrictDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListDistrictResponse  extends ResponseAbstract {

    private int count;
    private String provinceCode;
    private List<DistrictDTO> districtDTOs;
}
