package hcmute.tlcn.vtc.service.user.impl;

import hcmute.tlcn.vtc.model.dto.CustomerDTO;
import hcmute.tlcn.vtc.model.dto.FavoriteProductDTO;
import hcmute.tlcn.vtc.model.dto.ProductDTO;
import hcmute.tlcn.vtc.model.dto.ProductVariantDTO;
import hcmute.tlcn.vtc.model.dto.user.response.FavoriteProductResponse;
import hcmute.tlcn.vtc.model.dto.user.response.ListFavoriteProductResponse;
import hcmute.tlcn.vtc.model.dto.vendor.response.ProductResponse;
import hcmute.tlcn.vtc.model.entity.Customer;
import hcmute.tlcn.vtc.model.entity.FavoriteProduct;
import hcmute.tlcn.vtc.model.entity.Product;
import hcmute.tlcn.vtc.repository.FavoriteProductRepository;
import hcmute.tlcn.vtc.service.user.ICustomerService;
import hcmute.tlcn.vtc.service.user.IFavoriteProductService;
import hcmute.tlcn.vtc.service.user.IProductService;
import hcmute.tlcn.vtc.service.vendor.IProductShopService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteProductServiceImpl implements IFavoriteProductService {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductShopService productShopService;
    @Autowired
    private FavoriteProductRepository favoriteProductRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    @Transactional
    public FavoriteProductResponse addNewFavoriteProduct(Long productId, String username) {

        boolean isExist = favoriteProductRepository.existsByCustomerUsernameAndProductProductId(username, productId);
        if (isExist) {
            throw new IllegalArgumentException("Sản phẩm đã có trong danh sách yêu thích.");
        }

        Customer customer = customerService.getCustomerByUsername(username);
        Product product = productService.getProductById(productId);

        FavoriteProduct favoriteProduct = new FavoriteProduct();
        favoriteProduct.setCustomer(customer);
        favoriteProduct.setProduct(product);
        favoriteProduct.setCreateAt(LocalDateTime.now());

        try {
            FavoriteProduct saveFavoriteProduct = favoriteProductRepository.save(favoriteProduct);

            FavoriteProductDTO favoriteProductDTO = FavoriteProductDTO.convertToDTO(saveFavoriteProduct);
            FavoriteProductResponse response = new FavoriteProductResponse();
            response.setFavoriteProductDTO(favoriteProductDTO);
            response.setMessage("Thêm sản phẩm vào danh sách yêu thích thành công!");
            response.setStatus("success");
            response.setCode(200);
            return response;
        } catch (Exception e) {
            throw new IllegalArgumentException("Thêm sản phẩm vào danh sách yêu thích thất bại!");
        }

    }


    @Override
    public ProductResponse getProductByFavoriteProductId(Long favoriteProductId, String username) {
        FavoriteProduct favoriteProduct = favoriteProductRepository.findById(favoriteProductId)
                .orElseThrow(() -> new IllegalArgumentException("Sản phẩm yêu thích không tồn tại."));

        if (!favoriteProduct.getCustomer().getUsername().equals(username)) {
            throw new IllegalArgumentException("Sản phẩm yêu thích không thuộc sở hữu của bạn.");
        }

        ProductDTO productDTO = modelMapper.map(favoriteProduct.getProduct(), ProductDTO.class);
        productDTO.setProductVariantDTOs(ProductVariantDTO.convertToListDTO(favoriteProduct.getProduct().getProductVariants()));

        ProductResponse response = new ProductResponse();
        response.setProductDTO(productDTO);
        response.setMessage("Lấy thông tin sản phẩm yêu thích thành công.");
        response.setStatus("ok");
        response.setCode(200);

        return response;
    }


    @Override
    public ListFavoriteProductResponse getListFavoriteProduct(String username) {

        Customer customer = customerService.getCustomerByUsername(username);
        List<FavoriteProduct> listFavoriteProduct = favoriteProductRepository.findByCustomer(customer)
                .orElseThrow(() -> new IllegalArgumentException("Không có sản phẩm yêu thích nào!"));

        listFavoriteProduct.sort(Comparator.comparing(FavoriteProduct::getCreateAt).reversed());
        List<FavoriteProductDTO> favoriteProductDTOs = FavoriteProductDTO.convertToListDTO(listFavoriteProduct);

        ListFavoriteProductResponse response = new ListFavoriteProductResponse();
        response.setCustomerDTO(modelMapper.map(customer, CustomerDTO.class));
        response.setFavoriteProductDTOs(favoriteProductDTOs);
        response.setCount(listFavoriteProduct.size());
        response.setMessage("Lấy danh sách sản phẩm yêu thích thành công.");
        response.setStatus("ok");
        response.setCode(200);

        return response;
    }


    @Override
    public FavoriteProductResponse deleteFavoriteProduct(Long favoriteProductId, String username) {
        FavoriteProduct favoriteProduct = favoriteProductRepository.findById(favoriteProductId)
                .orElseThrow(() -> new IllegalArgumentException("Sản phẩm yêu thích không tồn tại."));

        if (!favoriteProduct.getCustomer().getUsername().equals(username)) {
            throw new IllegalArgumentException("Sản phẩm yêu thích không thuộc sở hữu của bạn.");
        }

        try {
            favoriteProductRepository.delete(favoriteProduct);
            FavoriteProductResponse response = new FavoriteProductResponse();
            response.setMessage("Xóa sản phẩm yêu thích thành công.");
            response.setStatus("ok");
            response.setCode(200);
            return response;
        } catch (Exception e) {
            throw new IllegalArgumentException("Xóa sản phẩm yêu thích thất bại.");
        }
    }


}
