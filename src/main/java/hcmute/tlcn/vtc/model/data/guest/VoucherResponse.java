package hcmute.tlcn.vtc.model.data.guest;

import hcmute.tlcn.vtc.model.dto.VoucherDTO;
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
