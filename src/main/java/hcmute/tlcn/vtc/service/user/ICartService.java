package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.dto.user.request.CartRequest;
import hcmute.tlcn.vtc.model.dto.user.response.CartResponse;
import org.springframework.transaction.annotation.Transactional;

public interface ICartService {
    @Transactional
    CartResponse addNewCart(CartRequest request);

    @Transactional
    CartResponse updateCart(CartRequest request);
}
