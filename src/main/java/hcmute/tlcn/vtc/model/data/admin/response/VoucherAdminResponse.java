package hcmute.tlcn.vtc.model.data.admin.response;
import hcmute.tlcn.vtc.model.data.dto.VoucherDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VoucherAdminResponse extends ResponseAbstract {

    private String username;

    private VoucherDTO voucherDTO;


}
