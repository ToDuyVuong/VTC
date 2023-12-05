package hcmute.tlcn.vtc.model.data.vendor.response;

import hcmute.tlcn.vtc.model.dto.ProductDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse extends ResponseAbstract {

    private String categoryName;
    private String shopName;
    private float rating;
    private ProductDTO productDTO;

}
