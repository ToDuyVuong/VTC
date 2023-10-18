package hcmute.tlcn.vtc.controller.admin;


import hcmute.tlcn.vtc.model.dto.admin.request.CategoryAdminRequest;
import hcmute.tlcn.vtc.model.dto.admin.response.AllCategoryAdminResponse;
import hcmute.tlcn.vtc.model.dto.admin.response.CategoryAdminResponse;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.service.admin.ICategoryAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/category")
@RequiredArgsConstructor
public class CategoryAdminController {

    @Autowired
    private ICategoryAdminService categoryAdminService;


    @PostMapping("/add")
    public ResponseEntity<CategoryAdminResponse> addNewCategoryByAdmin(
            @RequestBody CategoryAdminRequest request) {
        request.validate();
        return ResponseEntity.ok(categoryAdminService.addNewCategory(request));
    }


    @GetMapping("/parent/detail")
    public ResponseEntity<CategoryAdminResponse> getCategoryParentByAdmin(
            @RequestParam("categoryId") Long categoryId) {
        if (categoryId == null || categoryId == 0)
            throw new IllegalArgumentException("Mã danh mục không hợp lệ!");
        return ResponseEntity.ok(categoryAdminService.getCategoryParent(categoryId));
    }


    @GetMapping("/all-parent")
    public ResponseEntity<AllCategoryAdminResponse> getCategoryParent() {
        return ResponseEntity.ok(categoryAdminService.getAllCategoryParent());
    }


    @PutMapping("/update")
    public ResponseEntity<CategoryAdminResponse> updateCategoryParentByAdmin(
            @RequestBody CategoryAdminRequest request) {
        request.validate();
        if (request.getCategoryId() == null || request.getCategoryId() == 0)
            throw new IllegalArgumentException("Mã danh mục không hợp lệ!");
        return ResponseEntity.ok(categoryAdminService.updateCategoryParent(request));
    }


    @PatchMapping("/update/status")
    public ResponseEntity<CategoryAdminResponse> updateStatusCategoryParentByAdmin(
            @RequestParam Long categoryId, @RequestParam Status status) {
        if (categoryId == null || categoryId == 0)
            throw new IllegalArgumentException("Mã danh mục không hợp lệ!");
        return ResponseEntity.ok(categoryAdminService.updateStatusCategoryParent(categoryId, status));
    }




}
