package hcmute.tlcn.vtc.controller.admin;

import hcmute.tlcn.vtc.dto.admin.request.BrandAdminRequest;
import hcmute.tlcn.vtc.dto.admin.response.AllBrandAdminResponse;
import hcmute.tlcn.vtc.dto.admin.response.BrandAdminResponse;
import hcmute.tlcn.vtc.service.IBrandAdminService;
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
    public ResponseEntity<BrandAdminResponse> addNewBrand (@RequestBody BrandAdminRequest request){
        request.validate();
        BrandAdminResponse response = brandAdminService.addNewBrand(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{brandId}")
    public ResponseEntity<BrandAdminResponse> getBrandById(@PathVariable Long brandId){
        BrandAdminResponse response = brandAdminService.getBrandById(brandId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<AllBrandAdminResponse> getAllBrandAdmin(){
        AllBrandAdminResponse response = brandAdminService.getAllBrandAdmin();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<BrandAdminResponse> updateBrandAdmin(@RequestBody BrandAdminRequest request){
        request.validate();
        request.checkBrandId();
        BrandAdminResponse response = brandAdminService.updateBrandAdmin(request);
        return ResponseEntity.ok(response);
    }
}
