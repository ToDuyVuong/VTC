package hcmute.tlcn.vtc.controller.vendor;


import hcmute.tlcn.vtc.dto.vendor.request.RegisterShopRequest;
import hcmute.tlcn.vtc.dto.vendor.request.UpdateShopRequest;
import hcmute.tlcn.vtc.dto.vendor.response.ShopResponse;
import hcmute.tlcn.vtc.entity.extra.Status;
import hcmute.tlcn.vtc.service.IShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor")
@RequiredArgsConstructor
@Validated
public class ShopController {

    @Autowired
    IShopService shopService;


    @PostMapping("/register")
    public ResponseEntity<ShopResponse> registerShop(RegisterShopRequest request) {
        request.validate();
        ShopResponse response = shopService.registerShop(request);
        return ResponseEntity.ok(response);
    }



    @GetMapping("/shop/profile")
    public ResponseEntity<ShopResponse> getProfileShop(@RequestParam String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Tài khoản không được để trống!");
        }
        ShopResponse response = shopService.getProfileShop(username);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/shop/update")
    public ResponseEntity<ShopResponse> updateShop(UpdateShopRequest request) {
        request.validate();
        ShopResponse response = shopService.updateShop(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/shop/update/status")
    public ResponseEntity<ShopResponse> updateStatusShop(@RequestParam String username, @RequestParam Status status) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Tài khoản không được để trống!");
        }

        ShopResponse response = shopService.updateStatusShop(username, status);

        return ResponseEntity.ok(response);
    }
}
