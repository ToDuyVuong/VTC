package hcmute.tlcn.vtc.service.vendor.impl;

import hcmute.tlcn.vtc.model.dto.ProductDTO;
import hcmute.tlcn.vtc.model.dto.ProductVariantDTO;
import hcmute.tlcn.vtc.model.dto.vendor.request.ProductRequest;
import hcmute.tlcn.vtc.model.dto.vendor.response.ListProductResponse;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Transactional
    public ProductResponse addNewProduct(ProductRequest request) {
        Category category = categoryShopService.getCategoryShopById(request.getCategoryId(), request.getUsername());
        Brand brand = checkBrand(request.getBrandId());

        if (productRepository.existsByNameAndCategoryShopShopIdAndStatus(
                request.getName(), category.getShop().getShopId(), Status.ACTIVE)) {
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

            updateProductVariant(saveProduct, productVariants);

            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            productDTO.setProductVariantDTOs(ProductVariantDTO.convertToListDTO(productVariants));
            ProductResponse productResponse = new ProductResponse();
            productResponse.setProductDTO(productDTO);
            productResponse.setStatus("success");
            productResponse.setMessage("Thêm sản phẩm mới trong cửa hàng thành công!");
            productResponse.setCode(200);

            return productResponse;
        } catch (Exception e) {
            throw new SaveFailedException("Thêm sản phẩm mới trong cửa hàng thất bại!");
        }
    }


    @Override
    public ProductResponse getProductDetail(Long productId, String username) {
        Product product = getProductById(productId, username);
        ProductDTO productDTO;
        if (product.getStatus() == Status.DELETED) {
            productDTO = getProductDeleteToDTO(product);
        } else {
            productDTO = getProductToDTO(product);
        }

        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductDTO(productDTO);
        productResponse.setStatus("ok");
        productResponse.setCode(200);
        productResponse.setMessage("Lấy thông tin chi tiết sản phẩm thành công.");
        return productResponse;
    }


    @Override
    public ListProductResponse getListProductByUsername(String username) {
        Shop shop = shopService.getShopByUsername(username);

        List<Product> products = productRepository
                .findAllByCategoryShopShopIdAndStatus(shop.getShopId(), Status.ACTIVE)
                .orElseThrow(() -> new NotFoundException("Cửa hàng không có sản phẩm đang bán!"));

        List<ProductDTO> productDTOs = new ArrayList<>();

        for (Product product : products) {
            ProductDTO productDTO = getProductToDTO(product);
            productDTOs.add(productDTO);
        }

        ListProductResponse response = new ListProductResponse();
        response.setProductDTOs(productDTOs);
        response.setCount(productDTOs.size());
        response.setStatus("ok");
        response.setMessage("Lấy danh sách sản phẩm đang bán trong cửa hàng thành công.");
        response.setCode(200);

        return response;
    }


    @Override
    @Transactional
    public ProductResponse updateProduct(ProductRequest productRequest) {
        Product product = getProductById(productRequest.getProductId(), productRequest.getUsername());
        Category category = categoryShopService.getCategoryShopById(productRequest.getCategoryId(), productRequest.getUsername());

        Brand brand = checkBrand(productRequest.getBrandId());


        if (!product.getName().equals(productRequest.getName()) &&
                productRepository.existsByNameAndCategoryShopShopIdAndStatus(
                        productRequest.getName(), category.getShop().getShopId(), Status.ACTIVE)) {
            throw new IllegalArgumentException("Sản phẩm cập nhật đã có tên đã tồn tại trong cửa hàng!");
        }

        List<ProductVariant> productVariants = productVariantService.getListProductVariant(
                productRequest.getProductVariantRequests(),
                category.getShop().getShopId(), product.getProductId());

        product.setName(productRequest.getName());
        product.setImage(productRequest.getImage());
        product.setDescription(productRequest.getDescription());
        product.setInformation(productRequest.getInformation());
        product.setCategory(category);
        product.setBrand(brand);
        product.setUpdateAt(LocalDateTime.now());

        try {
            productRepository.save(product);

            List<ProductVariant> activeProductVariants = productVariants.stream()
                    .filter(productVariant -> productVariant.getStatus() == Status.ACTIVE)
                    .toList();

            updateProductVariant(product, productVariants);

            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            productDTO.setProductVariantDTOs(ProductVariantDTO.convertToListDTO(activeProductVariants));

            ProductResponse productResponse = new ProductResponse();
            productResponse.setProductDTO(productDTO);
            productResponse.setStatus("success");
            productResponse.setMessage("Cập nhật sản phẩm trong cửa hàng thành công!");
            productResponse.setCode(200);

            return productResponse;
        } catch (Exception e) {
            throw new SaveFailedException("Thêm sản phẩm mới trong cửa hàng thất bại!");
        }
    }


    @Override
    @Transactional
    public ProductResponse updateStatusProduct(Long productId, String username, Status status) {
        Product product = getProductById(productId, username);

        if (product.getStatus() == Status.DELETED) {
            throw new IllegalArgumentException("Sản phẩm đã bị xóa trong cửa hàng!");
        }

        product.setStatus(status);
        product.setUpdateAt(LocalDateTime.now());

        List<ProductVariant> activeProductVariants = product.getProductVariants().stream()
                .filter(productVariant -> productVariant.getStatus() != Status.DELETED)
                .toList();

        updateProductVariant(product, activeProductVariants);

        try {
            productRepository.save(product);

            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            productDTO.setProductVariantDTOs(ProductVariantDTO.convertToListDTO(activeProductVariants));
            ProductResponse productResponse = new ProductResponse();
            productResponse.setProductDTO(productDTO);
            productResponse.setStatus("success");
            productResponse.setMessage("Cập nhật trạng thái sản phẩm trong cửa hàng thành công!");
            productResponse.setCode(200);

            return productResponse;
        } catch (Exception e) {
            throw new SaveFailedException("Cập nhật trạng thái sản phẩm trong cửa hàng thất bại!");
        }

    }


    @Override
    @Transactional
    public ProductResponse restoreProductById(Long productId, String username) {
        Product product = getProductById(productId, username);

        if (product.getStatus() != Status.DELETED) {
            throw new IllegalArgumentException("Sản phẩm chưa bị xóa trong cửa hàng!");
        }

        List<ProductVariant> activeProductVariants = product.getProductVariants().stream()
                .filter(productVariant -> productVariant.getUpdateAt().equals(product.getUpdateAt()))
                .toList();

        product.setStatus(Status.ACTIVE);
        product.setUpdateAt(LocalDateTime.now());
        updateProductVariant(product, activeProductVariants);

        try {
            productRepository.save(product);

            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            productDTO.setProductVariantDTOs(ProductVariantDTO.convertToListDTO(activeProductVariants));
            ProductResponse productResponse = new ProductResponse();
            productResponse.setProductDTO(productDTO);
            productResponse.setStatus("success");
            productResponse.setMessage("Khôi phục sản phẩm trong cửa hàng thành công!");
            productResponse.setCode(200);

            return productResponse;
        } catch (Exception e) {
            throw new SaveFailedException("Khôi phục sản phẩm trong cửa hàng thất bại!");
        }

    }


    @Override
    public ListProductResponse getAllDeletedProduct(String username) {
        Shop shop = shopService.getShopByUsername(username);

        List<Product> products = productRepository
                .findAllByCategoryShopShopIdAndStatus(shop.getShopId(), Status.DELETED)
                .orElseThrow(() -> new NotFoundException("Cửa hàng không có sản phẩm đã xóa!"));

        List<ProductDTO> productDTOs = new ArrayList<>();

        for (Product product : products) {
            ProductDTO productDTO = getProductDeleteToDTO(product);
            productDTOs.add(productDTO);
        }

        ListProductResponse response = new ListProductResponse();
        response.setProductDTOs(productDTOs);
        response.setCount(productDTOs.size());
        response.setStatus("ok");
        response.setMessage("Lấy danh sách sản phẩm đã xóa trong cửa hàng thành công.");
        response.setCode(200);

        return response;
    }


    private ProductDTO getProductToDTO(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        List<ProductVariant> activeProductVariants = product.getProductVariants().stream()
                .filter(productVariant -> productVariant.getStatus() != Status.DELETED)
                .toList();

        productDTO.setProductVariantDTOs(ProductVariantDTO.convertToListDTO(activeProductVariants));
        return productDTO;
    }


    private Brand checkBrand(Long brandId) {
        if (brandId == null) {
            return null;
        }

        return brandRepository.findById(brandId)
                .orElseThrow(() -> new IllegalArgumentException("Thương hiệu không tồn tại!"));
    }


    private ProductDTO getProductDeleteToDTO(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        List<ProductVariant> activeProductVariants = product.getProductVariants().stream()
                .filter(productVariant -> productVariant.getUpdateAt().equals(product.getUpdateAt()))
                .toList();

        productDTO.setProductVariantDTOs(ProductVariantDTO.convertToListDTO(activeProductVariants));
        return productDTO;
    }


    private Product getProductById(Long productId, String username) {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new NotFoundException("Sản phẩm không tồn tại!"));

        if (product.getCategory().getShop() == null ||
                !product.getCategory().getShop().getCustomer().getUsername().equals(username)) {
            throw new NotFoundException("Sản phẩm không tồn tại trong cửa hàng!");
        }

        return product;
    }


    private void updateProductVariant(Product product, List<ProductVariant> productVariants) {
        for (ProductVariant productVariant : productVariants) {
            productVariant.setProduct(product);
            productVariant.setUpdateAt(product.getUpdateAt());
            try {
                productVariantRepository.save(productVariant);
            } catch (Exception e) {
                throw new SaveFailedException("Thêm biến thể sản phẩm thất bại!");
            }
        }
    }


}
