package hcmute.tlcn.vtc.model.dto.admin.response;

import hcmute.tlcn.vtc.model.dto.BrandDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BrandAdminResponse extends ResponseAbstract {

    private BrandDTO brandDTO;
}
