package hcmute.tlcn.vtc.model.dto.vendor.response;

import hcmute.tlcn.vtc.model.dto.AttributeDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AttributeResponse extends ResponseAbstract {

    private AttributeDTO attributeDTO;
}
