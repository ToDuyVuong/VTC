package hcmute.tlcn.vtc.repository.manager;

import hcmute.tlcn.vtc.model.entity.vtc.manager.ManagerProduct;
import hcmute.tlcn.vtc.model.entity.vtc.manager.ManagerShop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerShopRepository extends JpaRepository<ManagerShop, Long> {

    Optional<ManagerShop> findByShop_ShopId(Long shopId);

    int countAllByLock(boolean lock);

    Optional<Page<ManagerShop>> findAllByLock(boolean lock, Pageable pageable);

    int countAllByLockAndShopNameContains(boolean lock, String shopName);

    Optional<Page<ManagerShop>> findAllByLockAndShopNameContains(boolean lock, String shopName, Pageable pageable);

    boolean existsByShop_ShopId(Long shopId);

}
