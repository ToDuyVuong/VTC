package hcmute.tlcn.vtc.dto.admin.response;

import hcmute.tlcn.vtc.dto.BrandDTO;
import hcmute.tlcn.vtc.dto.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BrandAdminResponse extends ResponseAbstract {

    private BrandDTO brandDTO;
}
