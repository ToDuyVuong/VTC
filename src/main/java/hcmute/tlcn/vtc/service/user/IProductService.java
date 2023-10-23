package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.dto.vendor.response.ListProductResponse;
import hcmute.tlcn.vtc.model.dto.vendor.response.ProductResponse;

public interface IProductService {
    ProductResponse getProductDetail(Long productId);


    ListProductResponse getListProductByCategoryId(Long categoryId, boolean isParent);

    ListProductResponse getListProductByShopId(Long shopId);


    ListProductResponse getBestSellingProducts(Long shopId, int limit, boolean isShop);

    ListProductResponse getListNewProduct(Long shopId);

    ListProductResponse getListProductByPriceRange(Long shopId, Long minPrice, Long maxPrice);



    ListProductResponse searchProducts(Long shopId, String productName);
}
