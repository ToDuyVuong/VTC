package hcmute.tlcn.vtc.model.data.vendor.response;

import hcmute.tlcn.vtc.model.dto.VoucherDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListVoucherShopResponse extends ResponseAbstract {
    private Long shopId;
    private String shopName;
    private int count;
    private List<VoucherDTO> voucherDTOs;
}
