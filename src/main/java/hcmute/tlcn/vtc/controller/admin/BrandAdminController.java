package hcmute.tlcn.vtc.controller.admin;

import hcmute.tlcn.vtc.model.data.admin.request.BrandAdminRequest;
import hcmute.tlcn.vtc.model.data.admin.response.AllBrandAdminResponse;
import hcmute.tlcn.vtc.model.data.admin.response.BrandAdminResponse;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.service.admin.IBrandAdminService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/brand")
@RequiredArgsConstructor
public class BrandAdminController {

    @Autowired
    private IBrandAdminService brandAdminService;

    @PostMapping("/add")
    public ResponseEntity<BrandAdminResponse> addNewBrand(@RequestBody BrandAdminRequest brandAdminRequest,
                                                          HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        brandAdminRequest.setUsername(username);
        brandAdminRequest.validate();
        BrandAdminResponse response = brandAdminService.addNewBrand(brandAdminRequest);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{brandId}")
    public ResponseEntity<BrandAdminResponse> getBrandById(@PathVariable Long brandId) {
        BrandAdminResponse response = brandAdminService.getBrandById(brandId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<AllBrandAdminResponse> getAllBrandAdmin() {
        AllBrandAdminResponse response = brandAdminService.getAllBrandAdmin();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<BrandAdminResponse> updateBrandAdmin(@RequestBody BrandAdminRequest request,
                                                               HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        request.setUsername(username);
        request.validate();
        request.checkBrandId();
        BrandAdminResponse response = brandAdminService.updateBrandAdmin(request);
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/update-status/{brandId}")
    public ResponseEntity<BrandAdminResponse> updateStatusBrandAdmin(@PathVariable Long brandId,
                                                                     @RequestParam Status status,
                                                                     HttpServletRequest httpServletRequest) {

        String username = (String) httpServletRequest.getAttribute("username");

        if (brandId == null || brandId == 0) {
            throw new IllegalArgumentException("Mã thương hiệu không hợp lệ!");
        }
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Tên đăng nhập không được để trống!");
        }
        if (status == null) {
            throw new IllegalArgumentException("Trạng thái không được để trống!");
        }

        BrandAdminResponse response = brandAdminService.updateStatusBrandAdmin(brandId, username, status);
        return ResponseEntity.ok(response);
    }
}
