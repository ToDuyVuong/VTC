package hcmute.tlcn.vtc.model.data.location;

import hcmute.tlcn.vtc.model.dto.location_dto.ProvinceDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceResponse   extends ResponseAbstract {

        private ProvinceDTO provinceDTO;


}
