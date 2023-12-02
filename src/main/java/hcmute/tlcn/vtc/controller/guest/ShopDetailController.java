package hcmute.tlcn.vtc.controller.guest;

import hcmute.tlcn.vtc.model.data.guest.ShopDetailResponse;
import hcmute.tlcn.vtc.service.guest.IFollowedGuestService;
import hcmute.tlcn.vtc.service.guest.IShopDetailService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shop-detail")
@RequiredArgsConstructor
public class ShopDetailController {

    @Autowired
    private IShopDetailService shopDetailService;
    @Autowired
    private IFollowedGuestService followedGuestService;

    @GetMapping("/shop/{shopId}")
    public ResponseEntity<ShopDetailResponse> getShopDetailByShopId(@PathVariable Long shopId,
                                                                    HttpServletRequest request) {
        if (shopId == null) {
            throw new IllegalArgumentException("Mã cửa hàng không được để trống!");
        }
        String username = (String) request.getAttribute("username");
        return ResponseEntity.ok(shopDetailService.getShopDetailByShopId(shopId, username));
    }

    @GetMapping("/count-followed/{shopId}")
    public ResponseEntity<Integer> countFollowedShop(@PathVariable Long shopId) {
        if (shopId == null) {
            throw new IllegalArgumentException("Mã cửa hàng không được để trống!");
        }
        return ResponseEntity.ok(followedGuestService.countFollowedShop(shopId));
    }
}
