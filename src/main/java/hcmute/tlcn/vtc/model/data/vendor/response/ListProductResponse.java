package hcmute.tlcn.vtc.model.data.vendor.response;

import hcmute.tlcn.vtc.model.dto.ProductDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListProductResponse extends ResponseAbstract {

        private int count;

        private List<ProductDTO> productDTOs;
}
