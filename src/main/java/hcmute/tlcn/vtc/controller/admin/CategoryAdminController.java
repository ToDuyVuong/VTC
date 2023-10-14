package hcmute.tlcn.vtc.controller.admin;


import hcmute.tlcn.vtc.dto.admin.request.CategoryAdminRequest;
import hcmute.tlcn.vtc.dto.admin.response.CategoryAdminResponse;
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
    private ICategoryAdminService categoryService;

    @PostMapping("/add")
    public ResponseEntity<CategoryAdminResponse> addNewCategoryByAdmin(@RequestBody CategoryAdminRequest request) {
        request.validate();
        return ResponseEntity.ok(categoryService.addNewCategory(request));
    }




}
