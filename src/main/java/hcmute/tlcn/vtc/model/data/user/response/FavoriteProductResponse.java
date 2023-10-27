package hcmute.tlcn.vtc.model.data.user.response;

import hcmute.tlcn.vtc.model.data.dto.FavoriteProductDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteProductResponse extends ResponseAbstract {

    private FavoriteProductDTO favoriteProductDTO;


}
