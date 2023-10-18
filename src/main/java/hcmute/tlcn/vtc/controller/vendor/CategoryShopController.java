package hcmute.tlcn.vtc.controller.vendor;

import hcmute.tlcn.vtc.model.dto.admin.response.AllCategoryAdminResponse;
import hcmute.tlcn.vtc.model.dto.vendor.request.CategoryShopRequest;
import hcmute.tlcn.vtc.model.dto.vendor.response.ListCategoryShopResponse;
import hcmute.tlcn.vtc.model.dto.vendor.response.CategoryShopResponse;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.service.admin.ICategoryAdminService;
import hcmute.tlcn.vtc.service.vendor.ICategoryShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor/shop/category")
@RequiredArgsConstructor
public class CategoryShopController {

    @Autowired
    private ICategoryAdminService categoryAdminService;
    @Autowired
    private ICategoryShopService categoryService;


    @GetMapping("/all-parent")
    public ResponseEntity<AllCategoryAdminResponse> getParentCategory() {
        return ResponseEntity.ok(categoryAdminService.getAllCategoryParent());
    }


    @PostMapping("/add")
    public ResponseEntity<CategoryShopResponse> addNewCategoryShop(CategoryShopRequest request) {
        request.validate();
        CategoryShopResponse response = categoryService.addNewCategoryShop(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/all/{shopId}")
    public ResponseEntity<ListCategoryShopResponse> getAllCategoryByShopId(@PathVariable Long shopId) {
        if (shopId == null) {
            throw new NullPointerException("Mã cửa hàng không được để trống!");
        }
        ListCategoryShopResponse response = categoryService.getAllCategoryByShopId(shopId);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/get/{categoryId}")
    public ResponseEntity<CategoryShopResponse> getCategoryById(@PathVariable Long categoryId,
                                                                @RequestParam Long shopId) {
        if (categoryId == null) {
            throw new NullPointerException("Mã danh mục không được để trống!");
        }
        if (shopId == null) {
            throw new NullPointerException("Mã cửa hàng không được để trống!");
        }

        CategoryShopResponse response = categoryService.getCategoryById(categoryId, shopId);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/update")
    public ResponseEntity<CategoryShopResponse> updateCategoryShop(CategoryShopRequest request) {
        request.validateUpdate();
        CategoryShopResponse response = categoryService.updateCategoryShop(request);
        return ResponseEntity.ok(response);
    }


    @PatchMapping("update/status/{categoryId}")
    public ResponseEntity<CategoryShopResponse> updateStatusCategoryShop(@PathVariable Long categoryId,
                                                                         @RequestParam Long shopId,
                                                                         @RequestParam Status status) {
        if (categoryId == null) {
            throw new NullPointerException("Mã danh mục không được để trống!");

        }
        if (shopId == null) {
            throw new NullPointerException("Mã cửa hàng không được để trống!");
        }
        if (status == null) {
            throw new NullPointerException("Trạng thái không được để trống!");
        }

        CategoryShopResponse response = categoryService.updateStatusCategoryShop(categoryId, shopId, status);
        return ResponseEntity.ok(response);

    }


}
