package hcmute.tlcn.vtc.dto.vendor.response;

import hcmute.tlcn.vtc.dto.CategoryDTO;
import hcmute.tlcn.vtc.dto.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse extends ResponseAbstract {

    private CategoryDTO categoryDTO;
}
