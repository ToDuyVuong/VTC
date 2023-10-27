package hcmute.tlcn.vtc.model.data.user.response;

import hcmute.tlcn.vtc.model.data.dto.CategoryDTO;
import hcmute.tlcn.vtc.model.data.dto.ProductDTO;
import hcmute.tlcn.vtc.model.data.dto.ShopDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShopDetailResponse extends ResponseAbstract {

    ShopDTO shopDTO;

    int totalCategory;

    List<CategoryDTO> categoryDTOs;

    int totalProduct;

    List<ProductDTO> productDTOs;

    boolean isFollowed;
}
