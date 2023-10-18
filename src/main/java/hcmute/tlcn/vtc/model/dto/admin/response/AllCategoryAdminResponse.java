package hcmute.tlcn.vtc.model.dto.admin.response;

import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import hcmute.tlcn.vtc.model.dto.admin.CategoryAdminDTO;
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
