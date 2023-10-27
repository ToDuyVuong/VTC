package hcmute.tlcn.vtc.model.data.admin.response;

import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import hcmute.tlcn.vtc.model.data.admin.CategoryAdminDTO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryAdminResponse extends ResponseAbstract {

    private CategoryAdminDTO categoryAdminDTO;

}
