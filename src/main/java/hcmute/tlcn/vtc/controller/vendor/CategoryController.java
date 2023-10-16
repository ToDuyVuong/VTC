package hcmute.tlcn.vtc.controller.vendor;

import hcmute.tlcn.vtc.model.dto.admin.response.AllCategoryAdminResponse;
import hcmute.tlcn.vtc.model.dto.vendor.request.CategoryShopRequest;
import hcmute.tlcn.vtc.model.dto.vendor.response.CategoryShopResponse;
import hcmute.tlcn.vtc.service.admin.ICategoryAdminService;
import hcmute.tlcn.vtc.service.vendor.ICategoryShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vendor/shop/category")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private ICategoryAdminService categoryAdminService;
    @Autowired
    private ICategoryShopService categoryService;


    @GetMapping("/all-parent")
    public ResponseEntity<AllCategoryAdminResponse> getParentCategory() {
        return ResponseEntity.ok(categoryAdminService.getParentCategory());
    }


    @PostMapping("/add")
    public ResponseEntity<CategoryShopResponse> addNewCategoryShop(CategoryShopRequest request) {
        request.validate();
        CategoryShopResponse response = categoryService.addNewCategoryShop(request);
        return ResponseEntity.ok(response);
    }


}
