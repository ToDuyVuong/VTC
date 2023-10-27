package hcmute.tlcn.vtc.model.data.user.response;

import hcmute.tlcn.vtc.model.data.user.ListCartByShopDTO;
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
