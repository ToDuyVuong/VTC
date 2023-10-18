package hcmute.tlcn.vtc.controller.vendor;


import hcmute.tlcn.vtc.model.dto.vendor.request.ProductRequest;
import hcmute.tlcn.vtc.model.dto.vendor.response.ProductResponse;
import hcmute.tlcn.vtc.service.vendor.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vendor/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private IProductService productService;


    @PostMapping("/add")
    public ResponseEntity<ProductResponse> addNewProduct(@RequestBody  ProductRequest request) {
        request.validate();
        return ResponseEntity.ok(productService.addNewProduct(request));
    }


}
