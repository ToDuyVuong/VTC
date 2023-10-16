package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    int countByCategoryCategoryId(Long shopId);
}
