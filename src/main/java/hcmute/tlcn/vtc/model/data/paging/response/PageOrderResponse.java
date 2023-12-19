package hcmute.tlcn.vtc.model.data.paging.response;


import hcmute.tlcn.vtc.model.dto.OrderDTO;
import hcmute.tlcn.vtc.model.dto.ProductDTO;
import hcmute.tlcn.vtc.model.dto.ShopDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageOrderResponse extends ResponseAbstract {

    private int count;
    private int page;
    private int size;
    private int totalPage;
    private ShopDTO shopDTO;
    private List<OrderDTO> orderDTOs;
}
