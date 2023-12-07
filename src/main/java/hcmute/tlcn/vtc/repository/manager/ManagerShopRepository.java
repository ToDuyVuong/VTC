package hcmute.tlcn.vtc.repository.manager;

import hcmute.tlcn.vtc.model.entity.vtc.manager.ManagerShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerShopRepository extends JpaRepository<ManagerShop, Long> {
}
