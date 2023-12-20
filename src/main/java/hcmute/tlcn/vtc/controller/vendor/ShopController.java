package hcmute.tlcn.vtc.controller.vendor;


import hcmute.tlcn.vtc.model.data.vendor.request.RegisterShopRequest;
import hcmute.tlcn.vtc.model.data.vendor.request.UpdateShopRequest;
import hcmute.tlcn.vtc.model.data.vendor.response.ShopResponse;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.service.vendor.IShopService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ShopResponse> registerShop(@RequestBody RegisterShopRequest request,
                                                     HttpServletRequest httpServletRequest) {

        String username = (String) httpServletRequest.getAttribute("username");
        request.setUsername(username);
        request.validate();
        ShopResponse response = shopService.registerShop(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/shop/profile")
    public ResponseEntity<ShopResponse> getProfileShop(HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        ShopResponse response = shopService.getProfileShop(username);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/shop/update")
    public ResponseEntity<ShopResponse> updateShop(@RequestBody UpdateShopRequest request,
                                                   HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        request.setUsername(username);
        request.validate();

        ShopResponse response = shopService.updateShop(request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/shop/update/status")
    public ResponseEntity<ShopResponse> updateStatusShop(@RequestParam Status status,
                                                         HttpServletRequest httpServletRequest) {

        String username = (String) httpServletRequest.getAttribute("username");
        ShopResponse response = shopService.updateStatusShop(username, status);

        return ResponseEntity.ok(response);
    }
}
