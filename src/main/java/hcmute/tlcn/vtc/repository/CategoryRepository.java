package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByAdminOnly(boolean adminOnly);

    List<Category> findAllByParent(Category parent);

    Optional<Category> findByCategoryIdAndAdminOnly(Long categoryId, boolean adminOnly);

    Optional<Category> findByName(String name);

    Optional<Category> findByNameAndShopShopId(String name, Long shopId);

    Optional<Category> findByNameAndAdminOnly(String name, boolean adminOnly);

    Optional<Category> findByNameAndAdminOnlyNot(String name, boolean adminOnly);

    List<Category> findAllByShopShopId(Long shopId);





}
