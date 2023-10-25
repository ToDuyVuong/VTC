package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.Category;
import hcmute.tlcn.vtc.model.entity.Product;
import hcmute.tlcn.vtc.model.entity.Shop;
import hcmute.tlcn.vtc.model.extra.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    Shop findByEmail(String email);

    Shop findByPhone(String phone);

    Shop findByCustomer_Username(String username);

    Optional<Shop> findByCustomerUsername(String username);

    boolean existsByName(String name);

}
