package hcmute.tlcn.vtc.model.data.admin.response;

import hcmute.tlcn.vtc.model.data.dto.BrandDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AllBrandAdminResponse extends ResponseAbstract {

    private List<BrandDTO> brandDTOs;
}
