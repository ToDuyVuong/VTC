package hcmute.tlcn.vtc.model.dto.vendor.response;

import hcmute.tlcn.vtc.model.dto.CategoryDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryShopResponse extends ResponseAbstract {

    private CategoryDTO categoryDTO;
}
