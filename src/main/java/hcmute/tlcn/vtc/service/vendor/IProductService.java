package hcmute.tlcn.vtc.service.vendor;

import hcmute.tlcn.vtc.model.dto.vendor.request.ProductRequest;
import hcmute.tlcn.vtc.model.dto.vendor.response.ProductResponse;

public interface IProductService {
    ProductResponse addNewProduct(ProductRequest request);

    ProductResponse getProductDetail(Long productId, String username);
}
