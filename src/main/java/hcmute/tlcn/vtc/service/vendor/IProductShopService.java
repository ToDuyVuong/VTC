package hcmute.tlcn.vtc.service.vendor;

import hcmute.tlcn.vtc.model.dto.ProductDTO;
import hcmute.tlcn.vtc.model.dto.vendor.request.ProductRequest;
import hcmute.tlcn.vtc.model.dto.vendor.response.ListProductResponse;
import hcmute.tlcn.vtc.model.dto.vendor.response.ProductResponse;
import hcmute.tlcn.vtc.model.entity.Product;
import hcmute.tlcn.vtc.model.extra.Status;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IProductShopService {
    ProductResponse addNewProduct(ProductRequest request);

    ProductResponse getProductDetail(Long productId, String username);

    ListProductResponse getListProductByUsername(String username);

    ListProductResponse getListProductShopByCategoryId(Long categoryId, String username);

    ListProductResponse searchProductsByName(String productName, String username);

    ListProductResponse getBestSellingProducts(int limit, String username);

    ListProductResponse getListProductByPriceRange(String username, Long minPrice, Long maxPrice);

    ListProductResponse getListNewProduct(String username);

    ProductResponse updateProduct(ProductRequest productRequest);

    @Transactional
    ProductResponse updateStatusProduct(Long productId, String username, Status status);

    @Transactional
    ProductResponse restoreProductById(Long productId, String username);

    ListProductResponse getAllDeletedProduct(String username);



    ListProductResponse getListProductResponseSort(List<Product> products, String message, boolean isSort);

    ProductDTO getProductToDTO(Product product);
}
