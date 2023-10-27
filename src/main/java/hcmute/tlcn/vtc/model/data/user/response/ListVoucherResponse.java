package hcmute.tlcn.vtc.model.data.user.response;

import hcmute.tlcn.vtc.model.data.dto.VoucherDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListVoucherResponse extends ResponseAbstract {
    private int count;
    private List<VoucherDTO> voucherDTOs;
}
