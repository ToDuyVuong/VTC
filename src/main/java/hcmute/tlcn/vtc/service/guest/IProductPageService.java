package hcmute.tlcn.vtc.service.guest;

import hcmute.tlcn.vtc.model.data.paging.request.ProductPageRequest;
import hcmute.tlcn.vtc.model.data.paging.response.ListProductPageResponse;

public interface IProductPageService {

    ListProductPageResponse getListProductPageByCategoryId(Long categoryId, int page, int size);

    ListProductPageResponse getListProductsPageByShopId(Long shopId, int page, int size);

    ListProductPageResponse getListBestSellingProductsPageByShopId(Long shopId, int page, int size);

    ListProductPageResponse getListNewProductsPageByShopId(Long shopId, int page, int size);

    ListProductPageResponse getListProductsPagePriceRange(Long minPrice, Long maxPrice, int page, int size);

    ListProductPageResponse getListProductsPageByShopAndPriceRange(Long shopId, Long minPrice, Long maxPrice, int page, int size);

    ListProductPageResponse getListProductsPageByShopAndPriceRangeAndSort(Long shopId, Long minPrice, Long maxPrice, int page, int size, String sort);

    ListProductPageResponse getListProductsPageByShopSearchSort(String search, int page, int size, String sort);

    ListProductPageResponse getListProductsPageBySearchAndPriceSort(String search, Long minPrice, Long maxPrice, int page, int size, String sort);

    void checkRequestPageParams(int page, int size);

    void checkRequestPriceRangeParams(Long minPrice, Long maxPrice);

    void checkRequestSortParams(String sort);
}
