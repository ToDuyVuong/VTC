package hcmute.tlcn.vtc.controller.admin;

import hcmute.tlcn.vtc.dto.admin.request.BrandAdminRequest;
import hcmute.tlcn.vtc.dto.admin.response.BrandAdminResponse;
import hcmute.tlcn.vtc.service.IBrandAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
