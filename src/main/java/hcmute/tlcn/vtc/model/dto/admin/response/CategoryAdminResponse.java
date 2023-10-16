package hcmute.tlcn.vtc.model.dto.admin.response;

import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import hcmute.tlcn.vtc.model.dto.admin.CategoryAdminDTO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryAdminResponse extends ResponseAbstract {

    private CategoryAdminDTO categoryAdminDTO;

}
