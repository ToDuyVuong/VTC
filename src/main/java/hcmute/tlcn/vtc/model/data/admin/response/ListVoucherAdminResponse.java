package hcmute.tlcn.vtc.model.data.admin.response;

import hcmute.tlcn.vtc.model.dto.VoucherDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListVoucherAdminResponse extends ResponseAbstract {

    private String username;
    private int count;
    private List<VoucherDTO> voucherDTOs;
}
