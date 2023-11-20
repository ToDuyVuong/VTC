package hcmute.tlcn.vtc.controller.user;

import hcmute.tlcn.vtc.model.data.user.response.FollowedShopResponse;
import hcmute.tlcn.vtc.model.data.user.response.ListFollowedShopResponse;
import hcmute.tlcn.vtc.service.user.IFollowedShopService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer/followed-shop")
@RequiredArgsConstructor
public class FollowedShopController {

    @Autowired
    private IFollowedShopService followedShopService;


    @PostMapping("/add")
    public ResponseEntity<FollowedShopResponse> addNewFollowedShop(@RequestParam Long shopId,
                                                                   HttpServletRequest request) {
        if (shopId == null) {
            throw new IllegalArgumentException("Mã cửa hàng không được để trống!");
        }
        String username = (String) request.getAttribute("username");
        FollowedShopResponse response = followedShopService.addNewFollowedShop(shopId, username);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/list")
    public ResponseEntity<ListFollowedShopResponse> getListFollowedShop(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        return ResponseEntity.ok(followedShopService.getListFollowedShopByUsername(username));
    }


    @DeleteMapping("/delete/{followedShopId}")
    public ResponseEntity<FollowedShopResponse> deleteFollowedShopById(@PathVariable("followedShopId") Long followedShopId,
                                                                       HttpServletRequest request) {
        if (followedShopId == null) {
            throw new IllegalArgumentException("Mã cửa hàng theo dõi không được để trống!");
        }
        String username = (String) request.getAttribute("username");

        FollowedShopResponse response = followedShopService.deleteFollowedShop(followedShopId, username);
        return ResponseEntity.ok(response);
    }


}
