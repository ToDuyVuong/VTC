package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.dto.user.response.FavoriteProductResponse;
import hcmute.tlcn.vtc.model.dto.user.response.ListFavoriteProductResponse;
import hcmute.tlcn.vtc.model.dto.vendor.response.ProductResponse;
import org.springframework.transaction.annotation.Transactional;

public interface IFavoriteProductService {
    @Transactional
    FavoriteProductResponse addNewFavoriteProduct(Long productId, String username);

    ProductResponse getProductByFavoriteProductId(Long favoriteProductId, String username);

    ListFavoriteProductResponse getListFavoriteProduct(String username);

    FavoriteProductResponse deleteFavoriteProduct(Long favoriteProductId, String username);
}
