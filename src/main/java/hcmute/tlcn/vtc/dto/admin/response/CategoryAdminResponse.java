package hcmute.tlcn.vtc.dto.admin.response;

import hcmute.tlcn.vtc.dto.ResponseAbstract;
import hcmute.tlcn.vtc.dto.admin.CategoryAdminDTO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryAdminResponse extends ResponseAbstract {

    private CategoryAdminDTO categoryAdminDTO;

}
