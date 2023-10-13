package hcmute.tlcn.vtc.controller.admin;


import hcmute.tlcn.vtc.dto.admin.request.CategoryAdminRequest;
import hcmute.tlcn.vtc.dto.admin.response.AllCategoryAdminResponse;
import hcmute.tlcn.vtc.dto.admin.response.CategoryAdminResponse;
import hcmute.tlcn.vtc.dto.vendor.response.CategoryResponse;
import hcmute.tlcn.vtc.service.ICategoryAdminService;
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

//    @PostMapping("/add")
//    public ResponseEntity<CategoryResponse> addNewCategoryByAdmin(@RequestBody CategoryAdminRequest request) {
//        request.validate();
//        return ResponseEntity.ok(categoryService.addNewCategory(request));
//    }

    @PostMapping("/add")
    public ResponseEntity<CategoryAdminResponse> addNewCategoryByAdmin(@RequestBody CategoryAdminRequest request) {
        request.validate();
        return ResponseEntity.ok(categoryAdminService.addNewCategory(request));
    }



    @GetMapping("/parent")
    public ResponseEntity<AllCategoryAdminResponse> getParentCategory() {
        return ResponseEntity.ok(categoryAdminService.getParentCategory());
    }


}
