package hcmute.tlcn.vtc.controller.admin;


import hcmute.tlcn.vtc.dto.admin.request.CategoryAdminRequest;
import hcmute.tlcn.vtc.dto.vendor.response.CategoryResponse;
import hcmute.tlcn.vtc.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/category")
@RequiredArgsConstructor
public class CategoryAdminController {

    @Autowired
    private ICategoryService categoryService;

    @PutMapping("/add")
    public ResponseEntity<CategoryResponse> addNewCategoryByAdmin(@RequestBody CategoryAdminRequest request) {
        request.validate();
        return ResponseEntity.ok(categoryService.addNewCategory(request));
    }


}
