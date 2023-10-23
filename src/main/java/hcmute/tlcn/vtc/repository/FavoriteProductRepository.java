package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.Customer;
import hcmute.tlcn.vtc.model.entity.FavoriteProduct;
import hcmute.tlcn.vtc.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Long> {


    Optional<List<FavoriteProduct>> findByCustomer(Customer customer);

    boolean existsByCustomerUsernameAndProductProductId(String username, Long productId);





}
