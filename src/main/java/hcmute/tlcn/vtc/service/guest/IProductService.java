package hcmute.tlcn.vtc.service.guest;

import hcmute.tlcn.vtc.model.data.vendor.response.ListProductResponse;
import hcmute.tlcn.vtc.model.data.vendor.response.ProductResponse;
import hcmute.tlcn.vtc.model.entity.Product;

public interface IProductService {
    ProductResponse getProductDetail(Long productId);


    ListProductResponse getListProductByCategoryId(Long categoryId, boolean isParent);

    ListProductResponse getListProductByShopId(Long shopId);


    ListProductResponse getBestSellingProducts(Long shopId, int limit, boolean isShop);

    ListProductResponse getListNewProduct(Long shopId);

    ListProductResponse getListProductByPriceRange(Long shopId, Long minPrice, Long maxPrice);



    ListProductResponse searchProducts(Long shopId, String productName);

    Product getProductById(Long productId);
}
