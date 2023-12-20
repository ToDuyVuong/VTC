package hcmute.tlcn.vtc.controller.vendor;

import hcmute.tlcn.vtc.model.data.admin.response.AllCategoryAdminResponse;
import hcmute.tlcn.vtc.model.data.vendor.request.CategoryShopRequest;
import hcmute.tlcn.vtc.model.data.vendor.response.ListCategoryShopResponse;
import hcmute.tlcn.vtc.model.data.vendor.response.CategoryShopResponse;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.service.admin.ICategoryAdminService;
import hcmute.tlcn.vtc.service.vendor.ICategoryShopService;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<CategoryShopResponse> addNewCategoryShop(@RequestBody  CategoryShopRequest request,
                                                                   HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        request.setUsername(username);
        request.validate();
        CategoryShopResponse response = categoryService.addNewCategoryShop(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/all")
    public ResponseEntity<ListCategoryShopResponse> getAllCategoryByShopId(HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        ListCategoryShopResponse response = categoryService.getListCategoryShop(username);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/get/{categoryId}")
    public ResponseEntity<CategoryShopResponse> getCategoryById(@PathVariable Long categoryId,
                                                                HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        if (categoryId == null) {
            throw new NullPointerException("Mã danh mục không được để trống!");
        }

        CategoryShopResponse response = categoryService.getCategoryById(categoryId, username);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/update")
    public ResponseEntity<CategoryShopResponse> updateCategoryShop(@RequestBody CategoryShopRequest request,
                                                                   HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        request.setUsername(username);
        request.validateUpdate();
        CategoryShopResponse response = categoryService.updateCategoryShop(request);
        return ResponseEntity.ok(response);
    }


    @PatchMapping("update/status/{categoryId}")
    public ResponseEntity<CategoryShopResponse> updateStatusCategoryShop(@PathVariable Long categoryId,
                                                                         @RequestParam Status status,
                                                                         HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        if (categoryId == null) {
            throw new NullPointerException("Mã danh mục không được để trống!");
        }
        if (status == null) {
            throw new NullPointerException("Trạng thái không được để trống!");
        }

        CategoryShopResponse response = categoryService.updateStatusCategoryShop(categoryId, username, status);
        return ResponseEntity.ok(response);

    }


}
