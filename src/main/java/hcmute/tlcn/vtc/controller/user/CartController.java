package hcmute.tlcn.vtc.controller.user;

import hcmute.tlcn.vtc.model.dto.user.request.CartRequest;
import hcmute.tlcn.vtc.model.dto.user.response.CartResponse;
import hcmute.tlcn.vtc.service.user.ICartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
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

    @PutMapping
    public ResponseEntity<CartResponse> updateCart(@RequestBody CartRequest cartRequest, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        cartRequest.setUsername(username);
        cartRequest.validate();

        return ResponseEntity.ok(cartService.updateCart(cartRequest));
    }

}
