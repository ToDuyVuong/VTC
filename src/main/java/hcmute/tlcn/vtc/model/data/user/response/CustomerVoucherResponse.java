package hcmute.tlcn.vtc.model.data.user.response;

import hcmute.tlcn.vtc.model.data.dto.VoucherDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVoucherResponse  extends ResponseAbstract {
    private String username;
    private VoucherDTO voucherDTO;
}
