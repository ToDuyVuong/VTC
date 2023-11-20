package hcmute.tlcn.vtc.controller.user;


import hcmute.tlcn.vtc.model.data.user.response.FavoriteProductResponse;
import hcmute.tlcn.vtc.model.data.user.response.ListFavoriteProductResponse;
import hcmute.tlcn.vtc.model.data.vendor.response.ProductResponse;
import hcmute.tlcn.vtc.service.user.IFavoriteProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer/favorite-product")
@RequiredArgsConstructor
public class FavoriteProductController {

    @Autowired
    private IFavoriteProductService favoriteProductService;


    @PostMapping("/add")
    public ResponseEntity<FavoriteProductResponse> addNewFavoriteProduct(@RequestParam Long productId,
                                                                         HttpServletRequest request) {
        if (productId == null) {
            throw new IllegalArgumentException("Mã sản phẩm không được để trống!");
        }
        String username = (String) request.getAttribute("username");

        FavoriteProductResponse response = favoriteProductService.addNewFavoriteProduct(productId, username);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/detail/{favoriteProductId}")
    public ResponseEntity<ProductResponse> getFavoriteProductById(@PathVariable("favoriteProductId") Long favoriteProductId,
                                                                  HttpServletRequest request) {
        if (favoriteProductId == null) {
            throw new IllegalArgumentException("Mã sản phẩm yêu thích không được để trống!");
        }
        String username = (String) request.getAttribute("username");
        ProductResponse response = favoriteProductService.getProductByFavoriteProductId(favoriteProductId, username);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/list")
    public ResponseEntity<ListFavoriteProductResponse> getListFavoriteProduct(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        return ResponseEntity.ok(favoriteProductService.getListFavoriteProduct(username));
    }


    @DeleteMapping("/delete/{favoriteProductId}")
    public ResponseEntity<FavoriteProductResponse> deleteFavoriteProduct(@PathVariable("favoriteProductId") Long favoriteProductId,
                                                                         HttpServletRequest request) {
        if (favoriteProductId == null) {
            throw new IllegalArgumentException("Mã sản phẩm yêu thích không được để trống!");
        }
        String username = (String) request.getAttribute("username");
        FavoriteProductResponse response = favoriteProductService.deleteFavoriteProduct(favoriteProductId, username);
        return ResponseEntity.ok(response);
    }


}
