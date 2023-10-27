package hcmute.tlcn.vtc.model.data.vendor.response;

import hcmute.tlcn.vtc.model.data.dto.VoucherDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VoucherShopResponse extends ResponseAbstract {

    private Long shopId;
    private String shopName;

    private VoucherDTO voucherDTO;


}
