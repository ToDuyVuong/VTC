package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<List<Order>> findAllByCustomerUsername(String username);
}
