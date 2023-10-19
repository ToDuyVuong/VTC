package hcmute.tlcn.vtc.service.vendor.impl;

import hcmute.tlcn.vtc.model.dto.ProductDTO;
import hcmute.tlcn.vtc.model.dto.ProductVariantDTO;
import hcmute.tlcn.vtc.model.dto.vendor.request.ProductRequest;
import hcmute.tlcn.vtc.model.dto.vendor.response.ProductResponse;
import hcmute.tlcn.vtc.model.entity.*;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.repository.*;
import hcmute.tlcn.vtc.service.vendor.ICategoryShopService;
import hcmute.tlcn.vtc.service.vendor.IProductService;
import hcmute.tlcn.vtc.service.vendor.IProductVariantService;
import hcmute.tlcn.vtc.service.vendor.IShopService;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import hcmute.tlcn.vtc.util.exception.SaveFailedException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {


    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private IShopService shopService;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ICategoryShopService categoryShopService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductVariantRepository productVariantRepository;
    @Autowired
    private IProductVariantService productVariantService;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public ProductResponse addNewProduct(ProductRequest request) {
        Category category = categoryShopService.getCategoryShopById(request.getCategoryId(), request.getUsername());

        Brand brand = null;
        if (request.getBrandId() != null) {
            brand = brandRepository.findById(request.getBrandId())
                    .orElseThrow(() -> new IllegalArgumentException("Thương hiệu không tồn tại!"));
        }

        if (productRepository.existsByNameAndCategoryShopShopId(request.getName(), category.getShop().getShopId())) {
            throw new IllegalArgumentException("Sản phẩm có tên đã tồn tại trong cửa hàng!");
        }

        List<ProductVariant> productVariants = productVariantService.addNewListProductVariant(
                request.getProductVariantRequests(),
                category.getShop().getShopId());


        Product product = modelMapper.map(request, Product.class);
        product.setCategory(category);
        product.setBrand(brand);
        product.setSold(0L);
        product.setCreateAt(LocalDateTime.now());
        product.setUpdateAt(LocalDateTime.now());
        product.setStatus(Status.ACTIVE);

        try {
            Product saveProduct = productRepository.save(product);

            for (ProductVariant productVariant : productVariants) {
                productVariant.setProduct(saveProduct);
                try {
                    productVariantRepository.save(productVariant);
                } catch (Exception e) {
                    throw new SaveFailedException("Thêm biến thể sản phẩm thất bại!");
                }
            }

            List<ProductVariantDTO> productVariantDTOs = ProductVariantDTO.convertToListDTO(productVariants);
            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            productDTO.setProductVariantDTOs(productVariantDTOs);
            ProductResponse productResponse = new ProductResponse();
            productResponse.setProductDTO(productDTO);
            productResponse.setStatus("success");
            productResponse.setMessage("Thêm sản phẩm mới trong cửa hàng thành công!");
            productResponse.setCode(200);

            return productResponse;
        } catch (Exception e) {
            throw new SaveFailedException("Thêm sản phẩm mới trong cửa hàng thất bại!");
//            throw new IllegalArgumentException(e.getMessage());
        }
    }


    @Override
    public ProductResponse getProductDetail(Long productId, String username) {

        Product product = getProductById(productId, username);

        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        List<ProductVariantDTO> productVariantDTOs = ProductVariantDTO.convertToListDTO(product.getProductVariants());
        productDTO.setProductVariantDTOs(productVariantDTOs);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductDTO(productDTO);
        productResponse.setStatus("ok");
        productResponse.setCode(200);
        productResponse.setMessage("Lấy thông tin sản phẩm thành công!");
        return productResponse;
    }


    public Product getProductById(Long productId, String username) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Sản phẩm không tồn tại!"));

        if (product.getCategory().getShop() == null || !product.getCategory().getShop().getCustomer().getUsername().equals(username)) {
            throw new NotFoundException("Sản phẩm không tồn tại trong cửa hàng!");
        }

        return product;
    }


}
