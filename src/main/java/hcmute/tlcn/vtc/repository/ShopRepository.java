package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.vtc.Shop;
import hcmute.tlcn.vtc.model.extra.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    Shop findByEmail(String email);

    Shop findByPhone(String phone);

    Shop findByCustomer_Username(String username);

    Optional<Shop> findByCustomerUsername(String username);

    boolean existsByName(String name);


    int countAllByStatus(Status status);

    Optional<Page<Shop>> findAllByStatus(Status status, Pageable pageable);

    int countByNameContainsAndStatus(String name, Status status);

    Optional<Page<Shop>> findAllByNameContainsAndStatusOrderByName(String name, Status status, Pageable pageable);


}
