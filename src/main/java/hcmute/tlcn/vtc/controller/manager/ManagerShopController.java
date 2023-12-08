package hcmute.tlcn.vtc.controller.manager;

import hcmute.tlcn.vtc.model.data.manager.response.ListShopManagerResponse;
import hcmute.tlcn.vtc.model.data.manager.response.ManagerShopResponse;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.service.manager.IManagerShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manager/shop")
@RequiredArgsConstructor
public class ManagerShopController {

    @Autowired
    private IManagerShopService managerShopService;


    @GetMapping("/id/{shopId}")
    public ResponseEntity<ManagerShopResponse> getShopById(@PathVariable Long shopId) {
        return ResponseEntity.ok(managerShopService.getShopById(shopId));
    }


    @GetMapping("/list/status/{status}")
    public ResponseEntity<ListShopManagerResponse> getListShop(@RequestParam int page,
                                                               @RequestParam int size,
                                                               @PathVariable Status status) {
        managerShopService.checkRequestPageParams(page, size);

        return ResponseEntity.ok(managerShopService.getListShop(size, page, status));
    }

    @GetMapping("/list/name/{name}/status/{status}")
    public ResponseEntity<ListShopManagerResponse> getListShopByName(@RequestParam int page,
                                                                     @RequestParam int size,
                                                                     @PathVariable String name,
                                                                     @PathVariable Status status) {
        managerShopService.checkRequestPageParams(page, size);

        return ResponseEntity.ok(managerShopService.getListShopByName(size, page, name, status));
    }



}
