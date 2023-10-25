package hcmute.tlcn.vtc.service.user.impl;

import hcmute.tlcn.vtc.model.dto.CartDTO;
import hcmute.tlcn.vtc.model.dto.ProductDTO;
import hcmute.tlcn.vtc.model.dto.ProductVariantDTO;
import hcmute.tlcn.vtc.model.dto.user.request.CartRequest;
import hcmute.tlcn.vtc.model.dto.user.response.CartResponse;
import hcmute.tlcn.vtc.model.entity.Cart;
import hcmute.tlcn.vtc.model.entity.Customer;
import hcmute.tlcn.vtc.model.entity.ProductVariant;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.repository.CartRepository;
import hcmute.tlcn.vtc.repository.ProductVariantRepository;
import hcmute.tlcn.vtc.service.user.ICartService;
import hcmute.tlcn.vtc.service.user.ICustomerService;
import hcmute.tlcn.vtc.service.user.IProductService;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {

    @Autowired
    private final CartRepository cartRepository;
    @Autowired
    private final ICustomerService customerService;
    @Autowired
    private final IProductService productService;
    @Autowired
    private final ProductVariantRepository productVariantRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    @Transactional
    public CartResponse addNewCart(CartRequest request) {
        if (cartRepository.existsByProductVariantProductVariantIdAndCustomerUsername(
                request.getProductVariantId(), request.getUsername())) {
            return updateCart(request);
        }

        Customer customer = customerService.getCustomerByUsername(request.getUsername());
        ProductVariant productVariant = checkAndReturnProductVariant(request.getProductVariantId(), request.getQuantity());

        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setProductVariant(productVariant);
        cart.setQuantity(request.getQuantity());
        cart.setCreateAt(LocalDateTime.now());
        cart.setUpdateAt(LocalDateTime.now());
        cart.setStatus(Status.CART);

        try {
            Cart saveCart = cartRepository.save(cart);

            CartDTO cartDTO = mapCartDTO(saveCart);

            CartResponse response = new CartResponse();
            response.setCartDTO(cartDTO);
            response.setUsername(request.getUsername());
            response.setStatus("success");
            response.setMessage("Thêm sản phẩm vào giỏ hàng thành công.");
            response.setCode(200);

            return response;
        } catch (Exception e) {
            throw new IllegalArgumentException("Thêm sản phẩm vào giỏ hàng thất bại.");
        }
    }

    @Override
    @Transactional
    public CartResponse updateCart(CartRequest request) {

        Cart cart = cartRepository.findByProductVariantProductVariantIdAndCustomerUsername(
                        request.getProductVariantId(), request.getUsername())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm trong giỏ hàng."));

        int quantity = request.getQuantity() + cart.getQuantity();
        checkAndReturnProductVariant(request.getProductVariantId(), quantity);

        cart.setQuantity(quantity);
        cart.setUpdateAt(LocalDateTime.now());

        try {
            cartRepository.save(cart);

            CartDTO cartDTO = mapCartDTO(cart);

            CartResponse response = new CartResponse();
            response.setCartDTO(cartDTO);
            response.setUsername(request.getUsername());
            response.setStatus("success");
            response.setMessage("Cập nhật giỏ hàng thành công.");
            response.setCode(200);

            return response;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cập nhật giỏ hàng thất bại.");
        }
    }


    public CartDTO mapCartDTO(Cart cart) {

        ProductDTO productDTO = modelMapper.map(cart.getProductVariant().getProduct(), ProductDTO.class);
        productDTO.setProductVariantDTOs(ProductVariantDTO.convertToListDTO(
                cart.getProductVariant().getProduct().getProductVariants()));

        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
        cartDTO.setProductDTO(productDTO);
        cartDTO.setProductVariantDTO(ProductVariantDTO.mapEntityToDTO(cart.getProductVariant()));

        return cartDTO;
    }


    private ProductVariant checkAndReturnProductVariant(Long productVariantId, int quantity) {
        ProductVariant productVariant = productVariantRepository
                .findById(productVariantId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy biến thể sản phẩm!"));

        if (productVariant.getStatus() == Status.DELETED ||
                productVariant.getProduct().getStatus() == Status.DELETED) {
            throw new IllegalArgumentException("Sản phẩm đã bị xóa.");
        }

        if (productVariant.getQuantity() <= 0) {
            throw new IllegalArgumentException("Sản phẩm đã hết hàng.");
        }

        if (productVariant.getQuantity() < quantity) {
            throw new IllegalArgumentException("Số lượng sản phẩm trong kho không đủ.");
        }

        return productVariant;
    }

}
