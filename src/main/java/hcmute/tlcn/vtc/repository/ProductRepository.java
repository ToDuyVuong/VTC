package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.Product;
import hcmute.tlcn.vtc.model.extra.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    int countByCategoryCategoryId(Long shopId);

    boolean existsByBrandBrandId(Long brandId);

    boolean existsByNameAndCategoryShopShopIdAndStatus(String name, Long shopId, Status status);

    Optional<List<Product>> findAllByCategoryShopShopIdAndStatus(Long shopId, Status status);

    Optional<List<Product>> findAllByCategoryCategoryIdAndCategoryShopShopIdAndStatus(Long categoryId, Long shopId, Status status);

    Optional<List<Product>> findAllByNameContainingAndCategoryShopShopIdAndStatus(String name, Long shopId, Status status);



    @Query(value = "SELECT p.* FROM product p " +
            "WHERE p.category_shop_shop_id = :shopId AND p.status = :status " +
            "ORDER BY p.sold DESC, p.name ASC LIMIT :limit", nativeQuery = true)
    Optional<List<Product>> findBestSellingProducts(@Param("shopId") Long shopId,
                                          @Param("status") Status status,
                                          @Param("limit") int limit);


    Optional<List<Product>> findByCategoryShopShopIdAndStatusOrderBySoldDescNameAsc(Long shopId, Status status);



}
