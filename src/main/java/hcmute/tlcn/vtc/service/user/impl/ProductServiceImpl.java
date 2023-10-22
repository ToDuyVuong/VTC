package hcmute.tlcn.vtc.service.user.impl;

import hcmute.tlcn.vtc.model.dto.ProductDTO;
import hcmute.tlcn.vtc.model.dto.ProductVariantDTO;
import hcmute.tlcn.vtc.model.dto.vendor.response.ListProductResponse;
import hcmute.tlcn.vtc.model.dto.vendor.response.ProductResponse;
import hcmute.tlcn.vtc.model.entity.Product;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.repository.ProductRepository;
import hcmute.tlcn.vtc.service.user.IProductService;
import hcmute.tlcn.vtc.service.vendor.IProductShopService;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private IProductShopService productShopService;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ProductResponse getProductDetail(Long productId) {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new NotFoundException("Sản phẩm không tồn tại!"));

        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        productDTO.setProductVariantDTOs(ProductVariantDTO.convertToListDTO(product.getProductVariants()));

        ProductResponse response = new ProductResponse();
        response.setProductDTO(productDTO);
        response.setMessage("Lấy thông tin sản phẩm thành công!");
        response.setStatus("ok");
        response.setCode(200);
        return response;
    }


    @Override
    public ListProductResponse getListProductByCategoryId(Long categoryId, boolean isParent) {
        List<Product> products;
        if (isParent) {
            products = productRepository.findByCategoryParentCategoryIdAndStatus(categoryId, Status.ACTIVE)
                    .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm nào trong danh mục cha này!"));
        } else {
            products = productRepository.findByCategoryCategoryIdAndStatus(categoryId, Status.ACTIVE)
                    .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm nào trong danh mục này!"));
        }

        return productShopService.getListProductResponseSort(products,
                "Lấy danh sách sản phẩm thành công!",
                true);
    }


    @Override
    public ListProductResponse getListProductByShopId(Long shopId) {
        List<Product> products = productRepository.findByCategoryShopShopIdAndStatus(shopId, Status.ACTIVE)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm nào trong cửa hàng này!"));

        return productShopService.getListProductResponseSort(products,
                "Lấy danh sách sản phẩm thành công!",
                true);
    }


    @Override
    public ListProductResponse getBestSellingProducts(Long shopId, int limit, boolean isShop) {
        List<Product> products;
        if (isShop) {
            products = productRepository.findByCategoryShopShopIdAndStatusOrderBySoldDescNameAsc(shopId, Status.ACTIVE)
                    .orElseThrow(() -> new NotFoundException("Cửa hàng không có sản phẩm bán chạy!"));
        } else {
            products = productRepository.findByStatusOrderBySoldDescNameAsc(Status.ACTIVE)
                    .orElseThrow(() -> new NotFoundException("Không có sản phẩm bán chạy!"));
        }


        return responseWithLimit(products, limit, "Lấy danh sách sản phẩm bán chạy thành công!");
    }

    private ListProductResponse responseWithLimit(List<Product> products, int limit, String message) {
        ListProductResponse response = productShopService.getListProductResponseSort(products,
                message,
                false);

        if (response.getCount() > limit) {
            response.setProductDTOs(response.getProductDTOs().subList(0, limit));
            response.setCount(limit);
        }

        return response;
    }


    @Override
    public ListProductResponse getListNewProduct(Long shopId) {

        List<Product> products = productRepository.findByCategoryShopShopIdAndStatusOrderByCreateAtDesc(shopId, Status.ACTIVE)
                .orElseThrow(() -> new NotFoundException("Cửa hàng không có sản phẩm mới!"));

        return productShopService.getListProductResponseSort(products,
                "Lấy danh sách sản phẩm mới trong cửa hàng thành công.",
                false);
    }


    @Override
    public ListProductResponse getListProductByPriceRangeOnShop(Long shopId, Long minPrice, Long maxPrice) {
        List<Product> products = productRepository.findByPriceRange(shopId, Status.ACTIVE, minPrice, maxPrice)
                .orElseThrow(() -> new NotFoundException("Cửa hàng không có sản phẩm nào trong khoảng giá này!"));
        return productShopService.getListProductResponseSort(products, "Lọc sản phẩm theo giá trong cửa hàng thành công.", true);
    }


    @Override
    public ListProductResponse searchProductsByOnShop(Long shopId, String productName) {
        List<Product> products = productRepository
                .findAllByNameContainingAndCategoryShopShopIdAndStatus(productName, shopId, Status.ACTIVE)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm nào có tên tương tự!"));

        return productShopService.getListProductResponseSort(products,
                "Tìm kiếm sản phẩm theo tên trong cửa hàng thành công!",
                true);
    }


}
