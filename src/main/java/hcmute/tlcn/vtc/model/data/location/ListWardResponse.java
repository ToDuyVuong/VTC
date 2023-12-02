package hcmute.tlcn.vtc.model.data.location;

import hcmute.tlcn.vtc.model.dto.location_dto.WardDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListWardResponse  extends ResponseAbstract {

    private int count;
    private String districtCode;
    List<WardDTO> wardDTOs;
}