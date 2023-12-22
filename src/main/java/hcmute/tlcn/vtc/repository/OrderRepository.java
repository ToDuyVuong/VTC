package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.vtc.Order;
import hcmute.tlcn.vtc.model.extra.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Long sumPaymentTotalByShopIdAndStatusAndOrderDateBetween(Long shopId, Status status, Date startDate, Date endDate);

    Optional<List<Order>> findAllByCustomerUsername(String username);

    Optional<List<Order>> findAllByCustomerUsernameAndStatus(String username, Status status);

    Optional<Order> findByOrderIdAndStatus(Long orderId, Status status);

    Optional<List<Order>> findAllByShopId(Long shopId);

    Optional<List<Order>> findAllByShopIdAndStatus(Long shopId, Status status);

    Optional<List<Order>> findAllByShopIdAndOrderDateBetween(Long shopId, Date startOrderDate, Date endOrderDate);

    Optional<List<Order>> findAllByShopIdAndOrderDateBetweenAndStatus(Long shopId, Date startOrderDate, Date endOrderDate, Status status);


    int countAllByShopIdAndStatusAndOrderDateBetween(Long shopId, Status status, Date startDate, Date endDate);


    Optional<List<Order>> findAllByShopIdAndStatusAndOrderDateBetween(Long shopId, Status status, Date startDate, Date endDate);


    Optional<Page<Order>> findAllByShopIdOrderByCreateAtDesc(Long shopId, PageRequest pageRequest);

    Optional<Page<Order>> findAllByShopIdAndStatusOrderByCreateAtDesc(Long shopId, Status status, PageRequest pageRequest);


    int countAllByShopIdAndStatus(Long shopId, Status status);

    int countAllByShopId(Long shopId);


}
