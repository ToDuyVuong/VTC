package hcmute.tlcn.vtc.service.user.impl;

import hcmute.tlcn.vtc.model.dto.CartDTO;
import hcmute.tlcn.vtc.model.dto.ListCartByShopDTO;
import hcmute.tlcn.vtc.model.data.user.request.CartRequest;
import hcmute.tlcn.vtc.model.data.user.response.CartResponse;
import hcmute.tlcn.vtc.model.data.user.response.ListCartResponse;
import hcmute.tlcn.vtc.model.entity.vtc.Cart;
import hcmute.tlcn.vtc.model.entity.vtc.Customer;
import hcmute.tlcn.vtc.model.entity.vtc.ProductVariant;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.repository.CartRepository;
import hcmute.tlcn.vtc.repository.ProductVariantRepository;
import hcmute.tlcn.vtc.service.user.ICartService;
import hcmute.tlcn.vtc.service.user.ICustomerService;
import hcmute.tlcn.vtc.service.guest.IProductService;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {

    @Autowired
    private final CartRepository cartRepository;
    @Autowired
    private final ICustomerService customerService;
    @Autowired
    private final ProductVariantRepository productVariantRepository;



    @Override
    @Transactional
    public CartResponse addNewCart(CartRequest request) {
        if (cartRepository.existsByProductVariantProductVariantIdAndCustomerUsernameAndStatus(
                request.getProductVariantId(), request.getUsername(), Status.CART)) {
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
        cart.setCartId(null);

        try {
            Cart saveCart = cartRepository.save(cart);

            CartDTO cartDTO = CartDTO.convertEntityToDTO(saveCart);

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

        Cart cart = cartRepository.findByProductVariantProductVariantIdAndCustomerUsernameAndStatus(
                        request.getProductVariantId(), request.getUsername(), Status.CART)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm trong giỏ hàng."));

        if (request.getQuantity() <= 0) {
            return deleteCart(cart.getCartId(), request.getUsername());
        }
        checkAndReturnProductVariant(request.getProductVariantId(), request.getQuantity());

        cart.setQuantity(request.getQuantity());
        cart.setUpdateAt(LocalDateTime.now());

        try {
            cartRepository.save(cart);

            CartDTO cartDTO = CartDTO.convertEntityToDTO(cart);

            CartResponse response = new CartResponse();
            response.setCartDTO(cartDTO);
            response.setUsername(request.getUsername());
            response.setStatus("success");
            response.setMessage("Cập nhật giỏ hàng thành công.");
            response.setCode(200);

            return response;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cập nhật giỏ hàng thất bại." + e.getMessage());
        }
    }


    @Override
    @Transactional
    public CartResponse deleteCart(Long cartId, String username) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy giỏ hàng."));
        if (!cart.getCustomer().getUsername().equals(username)) {
            throw new IllegalArgumentException("Không thể xóa sản phẩm khỏi giỏ hàng của người khác.");
        }

        try {
            cartRepository.delete(cart);

            CartDTO cartDTO = CartDTO.convertEntityToDTO(cart);
            CartResponse response = new CartResponse();
            response.setCartDTO(cartDTO);
            response.setUsername(username);
            response.setStatus("success");
            response.setMessage("Xóa sản phẩm khỏi giỏ hàng thành công.");
            response.setCode(200);

            return response;
        } catch (Exception e) {
            throw new IllegalArgumentException("Xóa sản phẩm khỏi giỏ hàng thất bại.");
        }
    }


    @Override
    public ListCartResponse getListCartByUsername(String username) {
        List<Cart> carts = cartRepository.findAllByCustomerUsernameAndStatus(username, Status.CART)
                .orElseThrow(() -> new NotFoundException("Giỏ hàng trống."));

        return getListCartResponse(username, carts, "Lấy danh sách giỏ hàng của khách hàng thành công.");
    }


    @Override
    public ListCartResponse getListCartByUsernameAndListCartId(String username, List<Long> cartIds) {
        List<Cart> carts = cartRepository.findAllByCustomerUsernameAndStatusAndCartIdIn(username, Status.CART, cartIds)
                .orElseThrow(() -> new NotFoundException("Giỏ hàng trống."));


        return getListCartResponse(username, carts, "Lấy danh sách giỏ hàng theo danh sách mã giỏ hàng thành công.");
    }

    @Override
    public List<Cart> getListCartByUsernameAndIds(String username, List<Long> cartIds) {
        List<Cart> carts = cartRepository.findAllByCustomerUsernameAndStatusAndCartIdIn(username, Status.CART, cartIds)
                .orElseThrow(() -> new NotFoundException("Giỏ hàng trống."));


        return carts;
    }


    @Override
    public Cart getCartByUserNameAndId(String username, Long cartId) {
        Cart cart = cartRepository.findByCustomerUsernameAndCartId(username, cartId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy giỏ hàng."));
        if(cart == null){
            throw new NotFoundException("Không tìm thấy giỏ hàng.");
        }


        return cart;
    }

    @Override
    @Transactional
    public ListCartResponse deleteCartByShopId(Long shopId, String username) {
        List<Cart> carts = cartRepository
                .findAllByCustomerUsernameAndProductVariantProductCategoryShopShopIdAndStatus(username, shopId, Status.CART)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy giỏ hàng theo cửa hàng."));
        if (carts.isEmpty()) {
            throw new NotFoundException("Không tìm thấy giỏ hàng.");
        }

        try {
            cartRepository.deleteAll(carts);
            List<Cart> cartsUpdate = cartRepository.findAllByCustomerUsernameAndStatus(username, Status.CART)
                    .orElseThrow(() -> new NotFoundException("Giỏ hàng trống."));

            String message = "Xóa giỏ hàng theo cửa hàng thành công.";

            return getListCartResponse(username, cartsUpdate, message);
        } catch (Exception e) {
            throw new IllegalArgumentException("Xóa giỏ hàng thất bại.");
        }
    }


    @Override
    public boolean checkCartsSameShop(String username, List<Long> cartIds){

       List<Cart> carts  = getListCartByUsernameAndIds(username, cartIds);

        if (!carts.isEmpty()) {
            // Kiểm tra xem số lượng cửa hàng (shop) khác nhau trong danh sách các cart
            long distinctShopCount = carts.stream()
                    .map(cart -> cart.getProductVariant().getProduct().getCategory().getShop().getShopId())
                    .distinct()
                    .count();

            // Nếu có nhiều hơn một cửa hàng, nghĩa là các cart không thuộc cùng một shop
            return distinctShopCount <= 1;
        }

        // Nếu danh sách cart trống, hoặc không tìm thấy, bạn có thể xử lý tùy theo yêu cầu của mình
        return false;
    }


    private ListCartResponse getListCartResponse(String username, List<Cart> carts, String message) {
        ListCartResponse response = new ListCartResponse();
        response.setListCartByShopDTOs(ListCartByShopDTO.convertToListDTOByShop(carts));
        response.setCount(carts.size());
        response.setUsername(username);
        response.setStatus("ok");
        response.setMessage(message);
        response.setCode(200);
        return response;
    }


    private ProductVariant checkAndReturnProductVariant(Long productVariantId, int quantity) {
        ProductVariant productVariant = productVariantRepository
                .findById(productVariantId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy biến thể sản phẩm!"));

        if (productVariant.getStatus() == Status.DELETED ||
                productVariant.getProduct().getStatus() == Status.DELETED) {
            throw new IllegalArgumentException("Sản phẩm đã bị xóa. " + productVariant.getProduct().getProductId());
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
