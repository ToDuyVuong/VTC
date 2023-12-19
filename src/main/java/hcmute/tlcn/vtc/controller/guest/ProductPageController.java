package hcmute.tlcn.vtc.controller.guest;

import hcmute.tlcn.vtc.model.data.paging.request.ProductPageRequest;
import hcmute.tlcn.vtc.model.data.paging.response.ListProductPageResponse;
import hcmute.tlcn.vtc.service.guest.IProductPageService;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product/page")
@RequiredArgsConstructor
public class ProductPageController {

    @Autowired
    private IProductPageService productPageService;


    @GetMapping("/list")
    public ResponseEntity<ListProductPageResponse> getListProductPage(@RequestParam int page,
                                                                      @RequestParam int size) {
        productPageService.checkRequestPageParams(page, size);

        return ResponseEntity.ok(productPageService.getListProductPage(page, size));
    }


    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ListProductPageResponse> getListProductPageByCategoryId(@RequestParam int page,
                                                                                  @RequestParam int size,
                                                                                  @PathVariable Long categoryId) {
        if (categoryId == null) {
            throw new NotFoundException("Mã danh mục không được để trống!");
        }
        productPageService.checkRequestPageParams(page, size);

        return ResponseEntity.ok(productPageService.getListProductPageByCategoryId(categoryId, page, size));
    }


    @GetMapping("/shop/{shopId}")
    public ResponseEntity<ListProductPageResponse> getListProductPageByShopId(@RequestParam int page,
                                                                              @RequestParam int size,
                                                                              @PathVariable Long shopId) {
        if (shopId == null) {
            throw new NotFoundException("Mã cửa hàng không được để trống!");
        }
        productPageService.checkRequestPageParams(page, size);

        return ResponseEntity.ok(productPageService.getListProductsPageByShopId(shopId, page, size));
    }


    @GetMapping("/shop/{shopId}/best-selling")
    public ResponseEntity<ListProductPageResponse> getListBestSellingProductsPageByShopId(@RequestParam int page,
                                                                                          @RequestParam int size,
                                                                                          @PathVariable Long shopId) {
        if (shopId == null) {
            throw new NotFoundException("Mã cửa hàng không được để trống!");
        }
        productPageService.checkRequestPageParams(page, size);

        return ResponseEntity.ok(productPageService.getListBestSellingProductsPageByShopId(shopId, page, size));
    }


    @GetMapping("/shop/{shopId}/new")
    public ResponseEntity<ListProductPageResponse> getListNewProductsPageByShopId(@RequestParam int page,
                                                                                  @RequestParam int size,
                                                                                  @PathVariable Long shopId) {
        if (shopId == null) {
            throw new NotFoundException("Mã cửa hàng không được để trống!");
        }
        productPageService.checkRequestPageParams(page, size);

        return ResponseEntity.ok(productPageService.getListNewProductsPageByShopId(shopId, page, size));
    }

    @GetMapping("/price-range")
    public ResponseEntity<ListProductPageResponse> getListProductsPagePriceRange(@RequestParam int page,
                                                                                 @RequestParam int size,
                                                                                 @RequestParam Long minPrice,
                                                                                 @RequestParam Long maxPrice) {
        productPageService.checkRequestPageParams(page, size);
        productPageService.checkRequestPriceRangeParams(minPrice, maxPrice);

        return ResponseEntity.ok(productPageService.getListProductsPagePriceRange(minPrice, maxPrice, page, size));
    }

    @GetMapping("/shop/{shopId}/price-range")
    public ResponseEntity<ListProductPageResponse> getListProductsPageByShopAndPriceRange(@RequestParam int page,
                                                                                          @RequestParam int size,
                                                                                          @PathVariable Long shopId,
                                                                                          @RequestParam Long minPrice,
                                                                                          @RequestParam Long maxPrice) {
        if (shopId == null) {
            throw new NotFoundException("Mã cửa hàng không được để trống!");
        }
        productPageService.checkRequestPageParams(page, size);
        productPageService.checkRequestPriceRangeParams(minPrice, maxPrice);

        return ResponseEntity.ok(productPageService.getListProductsPageByShopAndPriceRange(shopId, minPrice, maxPrice, page, size));
    }

    @GetMapping("/shop/{shopId}/price-range/sort")
    public ResponseEntity<ListProductPageResponse> getListProductsPageByShopAndPriceRangeSort(@RequestParam int page,
                                                                                              @RequestParam int size,
                                                                                              @PathVariable Long shopId,
                                                                                              @RequestParam Long minPrice,
                                                                                              @RequestParam Long maxPrice,
                                                                                              @RequestParam String sort) {
        if (shopId == null) {
            throw new NotFoundException("Mã cửa hàng không được để trống!");
        }
        productPageService.checkRequestPageParams(page, size);
        productPageService.checkRequestPriceRangeParams(minPrice, maxPrice);
        productPageService.checkRequestSortParams(sort);

        return ResponseEntity.ok(productPageService.getListProductsPageByShopAndPriceRangeAndSort(shopId, minPrice, maxPrice, page, size, sort));
    }


    @GetMapping("/search/sort")
    public ResponseEntity<ListProductPageResponse> getListProductsPageByShopSearchSort(@RequestParam int page,
                                                                                       @RequestParam int size,
                                                                                       @RequestParam String search,
                                                                                       @RequestParam String sort) {
        productPageService.checkRequestPageParams(page, size);
        productPageService.checkRequestSortParams(sort);
        if (search == null) {
            throw new NotFoundException("Từ khóa tìm kiếm không được để trống!");
        }

        return ResponseEntity.ok(productPageService.getListProductsPageByShopSearchSort(search, page, size, sort));
    }


    @GetMapping("/search/price/sort")
    public ResponseEntity<ListProductPageResponse> getListProductsPageByShopSearchPriceSort(@RequestParam int page,
                                                                                            @RequestParam int size,
                                                                                            @RequestParam String search,
                                                                                            @RequestParam String sort,
                                                                                            @RequestParam Long minPrice,
                                                                                            @RequestParam Long maxPrice) {
        productPageService.checkRequestPageParams(page, size);
        productPageService.checkRequestSortParams(sort);
        if (search == null) {
            throw new NotFoundException("Từ khóa tìm kiếm không được để trống!");
        }

        return ResponseEntity.ok(productPageService.getListProductsPageBySearchAndPriceSort(search, minPrice, maxPrice, page, size, sort));
    }


}
