package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
