package hcmute.tlcn.vtc.controller.manager;

import hcmute.tlcn.vtc.model.data.manager.response.ListManagerProductResponse;
import hcmute.tlcn.vtc.model.data.manager.response.ManagerProductResponse;
import hcmute.tlcn.vtc.service.manager.IManagerProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manager/product")
@RequiredArgsConstructor
public class ManagerProductController {

    @Autowired
    private IManagerProductService managerProductService;


    @PostMapping("/lock/productId/{productId}")
    public ResponseEntity<ManagerProductResponse> lockProductByProductId(@PathVariable Long productId,
                                                                         @RequestParam String note,
                                                                         HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        if (productId == null || productId <= 0) {
            throw new IllegalArgumentException("Mã sản phẩm không hợp lệ");
        }
        return ResponseEntity.ok(managerProductService.lockProductByProductId(productId, username, note.trim()));
    }

    @PostMapping("/unlock/productId/{productId}")
    public ResponseEntity<ManagerProductResponse> unLockProductByProductId(@PathVariable Long productId,
                                                                           @RequestParam String note,
                                                                           HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        if (productId == null || productId <= 0) {
            throw new IllegalArgumentException("Mã sản phẩm không hợp lệ");
        }
        return ResponseEntity.ok(managerProductService.unLockProductByProductId(productId, username, note.trim()));
    }



    @GetMapping("/list")
    public ResponseEntity<ListManagerProductResponse> getListManagerProduct(@RequestParam int page,
                                                                            @RequestParam int size) {
        managerProductService.checkRequestPageParams(page, size);
        return ResponseEntity.ok(managerProductService.getListManagerProduct(page, size));
    }


    @GetMapping("/list/productName/{productName}")
    public ResponseEntity<ListManagerProductResponse> getListManagerProductByProductName(@RequestParam int page,
                                                                                         @RequestParam int size,
                                                                                         @PathVariable String productName) {
        managerProductService.checkRequestPageParams(page, size);
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên sản phẩm không hợp lệ");
        }
        return ResponseEntity.ok(managerProductService.getListManagerProductByProductName(page, size, productName.trim()));
    }

}
