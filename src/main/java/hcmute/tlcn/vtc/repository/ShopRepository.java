package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    Shop findByEmail(String email);

    Shop findByPhone(String phone);

    Shop findByCustomer_Username(String username);
}
