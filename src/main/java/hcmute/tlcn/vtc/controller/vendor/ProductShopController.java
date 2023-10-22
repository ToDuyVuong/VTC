package hcmute.tlcn.vtc.controller.vendor;


import hcmute.tlcn.vtc.model.dto.vendor.request.ProductRequest;
import hcmute.tlcn.vtc.model.dto.vendor.response.ListProductResponse;
import hcmute.tlcn.vtc.model.dto.vendor.response.ProductResponse;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.service.vendor.IProductShopService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor/product")
@RequiredArgsConstructor
public class ProductShopController {

    @Autowired
    private IProductShopService productService;


    @PostMapping("/add")
    public ResponseEntity<ProductResponse> addNewProduct(@RequestBody ProductRequest request,
                                                         HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        request.setUsername(username);
        request.validate();
        return ResponseEntity.ok(productService.addNewProduct(request));
    }


    @GetMapping("/detail/{productId}")
    public ResponseEntity<ProductResponse> getProductDetail(@PathVariable Long productId,
                                                            HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        return ResponseEntity.ok(productService.getProductDetail(productId, username));
    }


    @GetMapping("/list")
    public ResponseEntity<ListProductResponse> getListProductByUsername(HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        return ResponseEntity.ok(productService.getListProductByUsername(username));
    }


    @GetMapping("/list/category/{categoryId}")
    public ResponseEntity<ListProductResponse> getListProductShopByCategoryId(@PathVariable Long categoryId,
                                                                              HttpServletRequest httpServletRequest) {
        if (categoryId == null) {
            throw new NullPointerException("Mã danh mục không được để trống!");
        }
        String username = (String) httpServletRequest.getAttribute("username");
        return ResponseEntity.ok(productService.getListProductShopByCategoryId(categoryId, username));
    }


    @GetMapping("/search")
    public ResponseEntity<ListProductResponse> searchProductsByName(@RequestParam String productName,
                                                                    HttpServletRequest httpServletRequest) {
        if (productName == null) {
            throw new NullPointerException("Tên sản phẩm không được để trống!");
        }
        String username = (String) httpServletRequest.getAttribute("username");
        return ResponseEntity.ok(productService.searchProductsByName(productName, username));
    }


    @GetMapping("/best-sellers")
    public ResponseEntity<ListProductResponse> getBestSellingProducts(@RequestParam Long limit, HttpServletRequest httpServletRequest) {
        int maxLimit = Math.toIntExact(limit);
        if (maxLimit <= 0) {
            throw new IllegalArgumentException("Số lượng sản phẩm bán chạy nhất phải lớn hơn 0!");
        }
        String username = (String) httpServletRequest.getAttribute("username");
        return ResponseEntity.ok(productService.getBestSellingProducts(maxLimit, username));
    }


    @GetMapping("/price-range")
    public ResponseEntity<ListProductResponse> getListProductByPriceRange(@RequestParam Long minPrice,
                                                                          @RequestParam Long maxPrice,
                                                                          HttpServletRequest httpServletRequest) {
        if (minPrice == null || maxPrice == null) {
            throw new NullPointerException("Giá sản phẩm không được để trống!");
        }
        String username = (String) httpServletRequest.getAttribute("username");
        return ResponseEntity.ok(productService.getListProductByPriceRange(username, minPrice, maxPrice));
    }


    @GetMapping("/list-new")
    public ResponseEntity<ListProductResponse> getListNewProduct(HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        return ResponseEntity.ok(productService.getListNewProduct(username));
    }



    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long productId,
                                                         @RequestBody ProductRequest request,
                                                         HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        request.setUsername(username);
        request.setProductId(productId);
        request.validateUpdate();
        return ResponseEntity.ok(productService.updateProduct(request));
    }


    @PatchMapping("/update/status/{productId}")
    public ResponseEntity<ProductResponse> updateStatusProduct(@PathVariable Long productId,
                                                               @RequestParam Status status,
                                                               HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        return ResponseEntity.ok(productService.updateStatusProduct(productId, username, status));
    }


    @GetMapping("/all-deleted")
    public ResponseEntity<ListProductResponse> getAllDeletedProduct(HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        return ResponseEntity.ok(productService.getAllDeletedProduct(username));
    }


    @PutMapping("/restore/{productId}")
    public ResponseEntity<ProductResponse> restoreProductById(@PathVariable Long productId,
                                                          HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        return ResponseEntity.ok(productService.restoreProductById(productId, username));
    }





}
