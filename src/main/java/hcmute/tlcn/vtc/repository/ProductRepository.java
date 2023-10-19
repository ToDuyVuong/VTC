package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.Product;
import hcmute.tlcn.vtc.model.extra.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    int countByCategoryCategoryId(Long shopId);

    boolean existsByBrandBrandId(Long brandId);

    boolean existsByNameAndCategoryShopShopIdAndStatus(String name, Long shopId, Status status);

    Optional<List<Product>> findAllByCategoryShopShopIdAndStatus(Long shopId, Status status);

}
