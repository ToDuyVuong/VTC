package hcmute.tlcn.vtc.service.vendor;

import hcmute.tlcn.vtc.model.dto.ProductVariantDTO;
import hcmute.tlcn.vtc.model.dto.vendor.request.ProductVariantRequest;
import hcmute.tlcn.vtc.model.entity.Product;
import hcmute.tlcn.vtc.model.entity.ProductVariant;

import java.util.List;

public interface IProductVariantService {


    ProductVariant addNewProductVariant(ProductVariantRequest request, Product product, Long shopId);

    List<ProductVariant> addNewListProductVariant(List<ProductVariantRequest> productVariantRequests, Long productId);
}
