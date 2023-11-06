package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.data.user.request.CartRequest;
import hcmute.tlcn.vtc.model.data.user.response.CartResponse;
import hcmute.tlcn.vtc.model.data.user.response.ListCartResponse;
import hcmute.tlcn.vtc.model.entity.Cart;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICartService {
    @Transactional
    CartResponse addNewCart(CartRequest request);

    @Transactional
    CartResponse updateCart(CartRequest request);

    @Transactional
    CartResponse deleteCart(Long cartId, String username);

    ListCartResponse getListCartByUsername(String username);

    ListCartResponse getListCartByUsernameAndListCartId(String username, List<Long> cartIds);

    List<Cart> getListCartByUsernameAndIds(String username, List<Long> cartIds);

    Cart getCartByUserNameAndId(String username, Long cartId);

    @Transactional
    ListCartResponse deleteCartByShopId(Long shopId, String username);
}
