package hcmute.tlcn.vtc.controller.vendor;

import hcmute.tlcn.vtc.dto.admin.response.AllCategoryAdminResponse;
import hcmute.tlcn.vtc.service.ICategoryAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vendor/shop/category")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private ICategoryAdminService categoryService;

    @GetMapping("/all-parent")
    public ResponseEntity<AllCategoryAdminResponse> getParentCategory() {
        return ResponseEntity.ok(categoryService.getParentCategory());
    }
}
