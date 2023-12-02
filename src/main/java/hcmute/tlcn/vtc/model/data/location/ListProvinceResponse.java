package hcmute.tlcn.vtc.model.data.location;

import hcmute.tlcn.vtc.model.extra.ResponseAbstract;

import hcmute.tlcn.vtc.model.dto.location_dto.ProvinceDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListProvinceResponse extends ResponseAbstract {

        private int count;
        private List<ProvinceDTO> provinceDTOs;
}
