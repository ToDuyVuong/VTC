package hcmute.tlcn.vtc.model.data.admin.response;

import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import hcmute.tlcn.vtc.model.data.admin.CategoryAdminDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AllCategoryAdminResponse extends ResponseAbstract {

    private List<CategoryAdminDTO> categoryAdminDTOs;
}
