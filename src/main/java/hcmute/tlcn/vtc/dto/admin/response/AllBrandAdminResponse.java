package hcmute.tlcn.vtc.dto.admin.response;

import hcmute.tlcn.vtc.dto.BrandDTO;
import hcmute.tlcn.vtc.dto.extra.ResponseAbstract;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AllBrandAdminResponse extends ResponseAbstract {

    private List<BrandDTO> brandDTOS;
}
