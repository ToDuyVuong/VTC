package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.data.user.response.FavoriteProductResponse;
import hcmute.tlcn.vtc.model.data.user.response.ListFavoriteProductResponse;
import hcmute.tlcn.vtc.model.data.vendor.response.ProductResponse;
import org.springframework.transaction.annotation.Transactional;

public interface IFavoriteProductService {
    @Transactional
    FavoriteProductResponse addNewFavoriteProduct(Long productId, String username);

    ProductResponse getProductByFavoriteProductId(Long favoriteProductId, String username);

    ListFavoriteProductResponse getListFavoriteProduct(String username);

    FavoriteProductResponse deleteFavoriteProduct(Long favoriteProductId, String username);
}
