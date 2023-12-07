package hcmute.tlcn.vtc.model.data.manager.response;

import hcmute.tlcn.vtc.model.dto.ShopDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListShopManagerResponse extends ResponseAbstract {

        private int count;
        private int size;
        private int page;
        private int totalPage;
        private List<ShopDTO> shopDTOs;
}
