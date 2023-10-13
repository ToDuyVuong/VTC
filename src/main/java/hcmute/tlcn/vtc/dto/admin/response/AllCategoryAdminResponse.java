package hcmute.tlcn.vtc.dto.admin.response;

import hcmute.tlcn.vtc.dto.ResponseAbstract;
import hcmute.tlcn.vtc.dto.admin.CategoryAdminDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AllCategoryAdminResponse extends ResponseAbstract {

    private List<CategoryAdminDTO> categoryAdminDTOS;
}
