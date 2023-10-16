package hcmute.tlcn.vtc.dto.vendor.response;

import hcmute.tlcn.vtc.dto.CategoryDTO;
import hcmute.tlcn.vtc.dto.extra.ResponseAbstract;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListCategoryResponse extends ResponseAbstract {

    private List<CategoryDTO> categoryDTOS;
}
