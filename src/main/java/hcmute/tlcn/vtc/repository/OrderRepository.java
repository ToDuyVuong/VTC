package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
