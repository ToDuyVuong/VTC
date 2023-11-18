package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.Order;
import hcmute.tlcn.vtc.model.extra.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<List<Order>> findAllByCustomerUsername(String username);

    Optional<List<Order>> findAllByCustomerUsernameAndStatus(String username, Status status);

    Optional<Order> findByOrderIdAndStatus(Long orderId, Status status);
}
