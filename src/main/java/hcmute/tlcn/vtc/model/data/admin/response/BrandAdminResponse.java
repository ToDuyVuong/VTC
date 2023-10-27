package hcmute.tlcn.vtc.model.data.admin.response;

import hcmute.tlcn.vtc.model.data.dto.BrandDTO;
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
