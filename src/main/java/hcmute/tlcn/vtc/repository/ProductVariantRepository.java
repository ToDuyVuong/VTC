package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.Product;
import hcmute.tlcn.vtc.model.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

    ProductVariant findByProductVariantId(Long productVariantId);

    boolean existsBySkuAndProductProductId(String sku, Long productId);

    List<ProductVariant> findAllByProductProductId(Long productId);

    ProductVariant getProductByProductVariantId(Long productVariantId);



}
