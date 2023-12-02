package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.vtc.Product;
import hcmute.tlcn.vtc.model.extra.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Optional<List<Product>> findAllByNameContainingAndStatus(String name, Status status);


//    @Query(value = "SELECT p.* FROM product p " +
//            "WHERE p.category_shop_shop_id = :shopId AND p.status = :status " +
//            "ORDER BY p.sold DESC, p.name ASC LIMIT :limit", nativeQuery = true)
//    Optional<List<Product>> findBestSellingProducts(@Param("shopId") Long shopId,
//                                          @Param("status") Status status,
//                                          @Param("limit") int limit);


    Optional<List<Product>> findByCategoryShopShopIdAndStatusOrderBySoldDescNameAsc(Long shopId, Status status);

    Optional<List<Product>> findByStatusOrderBySoldDescNameAsc(Status status);

    Optional<List<Product>> findByCategoryShopShopIdAndStatusOrderByCreateAtDesc(Long shopId, Status status);

    Optional<List<Product>> findByStatusOrderByCreateAtDesc(Status status);


    @Query("SELECT p FROM Product p JOIN p.productVariants v " +
            "WHERE p.category.shop.shopId = :shopId " +
            "AND p.status = :status " +
            "AND v.status = :status " +
            "AND v.price >= :minPrice " +
            "AND v.price <= :maxPrice")
    Optional<List<Product>> findByPriceRange(@Param("shopId") Long shopId,
                                             @Param("status") Status status,
                                             @Param("minPrice") Long minPrice,
                                             @Param("maxPrice") Long maxPrice);


    Optional<List<Product>> findByCategoryParentCategoryIdAndStatus(Long categoryParentId, Status status);

    Optional<List<Product>> findByCategoryCategoryIdAndStatus(Long categoryId, Status status);

    Optional<List<Product>> findByCategoryShopShopIdAndStatus(Long shopId, Status status);



    @Query("SELECT p FROM Product p JOIN p.productVariants v " +
            "WHERE p.status = :status " +
            "AND v.status = :status " +
            "AND v.price >= :minPrice " +
            "AND v.price <= :maxPrice")
    Optional<List<Product>> findByPriceRange(@Param("status") Status status,
                                             @Param("minPrice") Long minPrice,
                                             @Param("maxPrice") Long maxPrice);



    @Query("SELECT p FROM Product p " +
            "WHERE p.status = :status " +
            "ORDER BY p.createAt DESC")
    Optional<Page<Product>> findNewestProducts(@Param("status") Status status, Pageable pageable);








}
