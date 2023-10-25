package hcmute.tlcn.vtc.model.dto.user.response;

import hcmute.tlcn.vtc.model.dto.CartDTO;
import hcmute.tlcn.vtc.model.dto.user.ListCartByShopDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListCartResponse extends ResponseAbstract {

    private String username;

    int count;

    private List<ListCartByShopDTO> listCartByShopDTOs;
}
