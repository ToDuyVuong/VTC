package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.vtc.FollowedShop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowedShopRepository extends JpaRepository<FollowedShop, Long> {

    boolean existsByCustomerUsernameAndShopShopId(String username, Long shopId);

    Optional<List<FollowedShop>> findAllByCustomerUsername(String username);

    int countByShopShopId(Long shopId);
}