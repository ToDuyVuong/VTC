package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByAdminOnly(boolean adminOnly);
}
