package hcmute.tlcn.vtc.controller.vendor;


import hcmute.tlcn.vtc.dto.vendor.request.RegisterShopRequest;
import hcmute.tlcn.vtc.dto.vendor.response.ShopResponse;
import hcmute.tlcn.vtc.service.IShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vendor/shop")
@RequiredArgsConstructor
public class ShopController {

    @Autowired
    IShopService shopService;


    @PostMapping("/register")
    public ResponseEntity<ShopResponse> registerShop(RegisterShopRequest request) {
        request.validate();
        ShopResponse response = shopService.registerShop(request);
        return ResponseEntity.ok(response);
    }
}
