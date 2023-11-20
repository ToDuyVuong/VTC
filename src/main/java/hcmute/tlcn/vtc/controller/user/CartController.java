package hcmute.tlcn.vtc.controller.user;

import hcmute.tlcn.vtc.model.data.user.request.CartRequest;
import hcmute.tlcn.vtc.model.data.user.response.CartResponse;
import hcmute.tlcn.vtc.model.data.user.response.ListCartResponse;
import hcmute.tlcn.vtc.service.user.ICartService;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer/cart")
@RequiredArgsConstructor
public class CartController {

    @Autowired
    private final ICartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartResponse> addNewCart(@RequestBody CartRequest cartRequest, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        cartRequest.setUsername(username);
        cartRequest.validate();

        return ResponseEntity.ok(cartService.addNewCart(cartRequest));
    }

    @PutMapping("/update")
    public ResponseEntity<CartResponse> updateCart(@RequestBody CartRequest cartRequest, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        cartRequest.setUsername(username);
        cartRequest.validateUpdate();

        return ResponseEntity.ok(cartService.updateCart(cartRequest));
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<CartResponse> deleteCart(@PathVariable Long cartId, HttpServletRequest request) {
        if (cartId == null || cartId <= 0) {
            throw new NotFoundException("Mã giỏ hàng không hợp lệ!");
        }
        String username = (String) request.getAttribute("username");
        return ResponseEntity.ok(cartService.deleteCart(cartId, username));
    }

    @GetMapping("/get-list")
    public ResponseEntity<ListCartResponse> getListCartByUsername(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        return ResponseEntity.ok(cartService.getListCartByUsername(username));
    }


    @GetMapping("/get-list-by-list-cart-id")
    public ResponseEntity<ListCartResponse> getListCartByUsernameAndListCartId(@RequestParam List<Long> cartIds,
                                                                               HttpServletRequest request) {
        if (cartIds == null || cartIds.isEmpty()) {
            throw new NotFoundException("Danh sách mã giỏ hàng không hợp lệ!");
        }
        String username = (String) request.getAttribute("username");

        return ResponseEntity.ok(cartService.getListCartByUsernameAndListCartId(username, cartIds));
    }


    @DeleteMapping("/delete-by-shop-id/{shopId}")
    public ResponseEntity<ListCartResponse> deleteCartByShopId(@PathVariable Long shopId, HttpServletRequest request) {
        if (shopId == null || shopId <= 0) {
            throw new NotFoundException("Mã cửa hàng không hợp lệ!");
        }
        String username = (String) request.getAttribute("username");
        return ResponseEntity.ok(cartService.deleteCartByShopId(shopId, username));
    }


}
