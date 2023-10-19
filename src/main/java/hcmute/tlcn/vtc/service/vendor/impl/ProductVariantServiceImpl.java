package hcmute.tlcn.vtc.service.vendor.impl;

import hcmute.tlcn.vtc.model.dto.vendor.request.ProductVariantRequest;
import hcmute.tlcn.vtc.model.entity.Attribute;
import hcmute.tlcn.vtc.model.entity.Product;
import hcmute.tlcn.vtc.model.entity.ProductVariant;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.repository.*;
import hcmute.tlcn.vtc.service.user.impl.CustomerServiceImpl;
import hcmute.tlcn.vtc.service.vendor.IAttributeService;
import hcmute.tlcn.vtc.service.vendor.IProductVariantService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductVariantServiceImpl implements IProductVariantService {


    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private IAttributeService attributeService;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductVariantRepository productVariantRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public ProductVariant addNewProductVariant(ProductVariantRequest request, Product product, Long shopId) {

        request.validate();
        List<Attribute> attributes = new ArrayList<>();
        if (request.getAttributeIds() != null && !request.getAttributeIds().isEmpty()) {
            attributes = attributeService.getListAttributeByListId(request.getAttributeIds(), shopId);
        }

        ProductVariant productVariant = modelMapper.map(request, ProductVariant.class);
        productVariant.setProduct(product);
        productVariant.setStatus(Status.ACTIVE);
        productVariant.setAttributes(attributes);
        productVariant.setCreateAt(LocalDateTime.now());
        productVariant.setUpdateAt(LocalDateTime.now());

        try {
            return  productVariantRepository.save(productVariant);
        } catch (Exception e) {
            throw new IllegalArgumentException("Thêm biến thể sản phẩm thất bại!");

        }
    }


    @Override
    public List<ProductVariant> addNewListProductVariant(List<ProductVariantRequest> requests, Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Sản phẩm không tồn tại trong cửa hàng!"));
        Long shopId = product.getCategory().getShop().getShopId();

        List<ProductVariant> productVariants = new ArrayList<>();
        for (ProductVariantRequest request : requests) {
            productVariants.add(addNewProductVariant(request, product, shopId));
        }
        return productVariants;
    }




}
