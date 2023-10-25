package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.dto.user.request.CartRequest;
import hcmute.tlcn.vtc.model.dto.user.response.CartResponse;
import hcmute.tlcn.vtc.model.dto.user.response.ListCartResponse;
import org.springframework.transaction.annotation.Transactional;

public interface ICartService {
    @Transactional
    CartResponse addNewCart(CartRequest request);

    @Transactional
    CartResponse updateCart(CartRequest request);

    @Transactional
    CartResponse deleteCart(Long cartId, String username);

    ListCartResponse getListCartByUsername(String username);
}
