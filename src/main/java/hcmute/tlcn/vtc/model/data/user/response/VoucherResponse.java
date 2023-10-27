package hcmute.tlcn.vtc.model.data.user.response;

import hcmute.tlcn.vtc.model.data.dto.VoucherDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VoucherResponse extends ResponseAbstract {
    private VoucherDTO voucherDTO;
}
