package hcmute.tlcn.vtc.model.dto.user.response;

import hcmute.tlcn.vtc.model.dto.CustomerDTO;
import hcmute.tlcn.vtc.model.dto.FavoriteProductDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListFavoriteProductResponse extends ResponseAbstract {

    int count;

    private CustomerDTO customerDTO;

    private List<FavoriteProductDTO> favoriteProductDTOs;


}
