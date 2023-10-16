package hcmute.tlcn.vtc.model.dto.vendor.response;

import hcmute.tlcn.vtc.model.dto.CategoryDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListCategoryShopResponse extends ResponseAbstract {

    private List<CategoryDTO> categoryDTOS;
}
