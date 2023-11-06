package hcmute.tlcn.vtc.model.data.guest;

import hcmute.tlcn.vtc.model.dto.CategoryDTO;
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
public class ShopDetailResponse extends ResponseAbstract {

    ShopDTO shopDTO;

    int totalCategory;

    List<CategoryDTO> categoryDTOs;

    int totalProduct;

    List<ProductDTO> productDTOs;

    boolean isFollowed;
}
