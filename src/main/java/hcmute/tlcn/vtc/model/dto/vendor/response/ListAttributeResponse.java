package hcmute.tlcn.vtc.model.dto.vendor.response;


import hcmute.tlcn.vtc.model.dto.AttributeDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListAttributeResponse extends ResponseAbstract {

    private List<AttributeDTO> attributeDTOs;
}