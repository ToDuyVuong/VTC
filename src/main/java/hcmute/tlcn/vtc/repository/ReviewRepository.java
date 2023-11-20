package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.Review;
import hcmute.tlcn.vtc.model.extra.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    boolean existsByOrderItemOrderItemId(Long orderItemId);

    boolean existsByOrderItemOrderItemIdAndCustomerUsername(Long orderItemId, String username);

    Optional<Review> findByOrderItemOrderItemId(Long orderItemId);

    Optional<Review> findByReviewIdAndStatus(Long reviewId, Status status);

    Optional<List<Review>> findAllByProductProductIdAndStatus(Long productId, Status status);

    Optional<List<Review>> findAllByProductProductId(Long productId);

    Optional<List<Review>> findAllByProductProductIdAndRatingAndStatus(Long productId, int rating, Status status);

}
