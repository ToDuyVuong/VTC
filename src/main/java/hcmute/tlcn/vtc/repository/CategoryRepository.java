package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.Category;
import hcmute.tlcn.vtc.model.extra.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByAdminOnlyAndStatus(boolean adminOnly, Status status);

    List<Category> findAllByParent(Category parent);

    Optional<Category> findByCategoryIdAndAdminOnly(Long categoryId, boolean adminOnly);

    Optional<Category> findByName(String name);

    Optional<Category> findByNameAndShopCustomerUsername(String name, String username);

    Optional<Category> findByNameAndAdminOnly(String name, boolean adminOnly);

    Optional<Category> findByNameAndAdminOnlyNot(String name, boolean adminOnly);

    List<Category> findAllByShopShopId(Long shopId);


    List<Category> findAllByShopCustomerUsernameAndStatus(String username, Status status);




}
