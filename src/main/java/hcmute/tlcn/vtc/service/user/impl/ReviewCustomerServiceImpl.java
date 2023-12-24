package hcmute.tlcn.vtc.service.user.impl;

import hcmute.tlcn.vtc.model.data.user.request.ReviewRequest;
import hcmute.tlcn.vtc.model.data.user.response.ReviewResponse;
import hcmute.tlcn.vtc.model.dto.ReviewDTO;
import hcmute.tlcn.vtc.model.entity.vtc.OrderItem;
import hcmute.tlcn.vtc.model.entity.vtc.Product;
import hcmute.tlcn.vtc.model.entity.vtc.Review;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.repository.OrderItemRepository;
import hcmute.tlcn.vtc.repository.ReviewRepository;
import hcmute.tlcn.vtc.service.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewCustomerServiceImpl implements IReviewCustomerService {

    @Autowired
    private final ReviewRepository reviewRepository;
    @Autowired
    private final ICustomerService customerService;
    @Autowired
    private OrderItemRepository orderItemRepository;



    @Override
    @Transactional
    public ReviewResponse addNewReview(ReviewRequest request, String username) {
        checkOrderItemAndRoleReviewAndStatus(request.getOrderItemId(), username);

        OrderItem orderItem = orderItemRepository.findById(request.getOrderItemId())
                .orElseThrow(() -> new IllegalArgumentException("Sản phẩm không tồn tại!"));

        Product product = orderItem.getCart().getProductVariant().getProduct();

        Review review = new Review();
        review.setOrderItem(orderItem);
        review.setRating(request.getRating());
        review.setContent(request.getContent());
        review.setImage(request.getImage());
        review.setStatus(Status.ACTIVE);
        review.setCustomer(customerService.getCustomerByUsername(username));
        review.setProduct(product);
        review.setCreateAt(LocalDateTime.now());
        review.setUpdateAt(LocalDateTime.now());
        try {
            reviewRepository.save(review);
            return reviewResponse(review, username, "Thêm mới đánh giá thành công!", false);
        } catch (Exception e) {
            throw new IllegalArgumentException("Thêm mới đánh giá thất bại!"+ e.getMessage());
        }
    }


    @Override
    public ReviewResponse getReviewByOrderItemId(Long orderItemId) {
        Review review = reviewRepository.findByOrderItemOrderItemId(orderItemId)
                .orElseThrow(() -> new IllegalArgumentException("Đánh giá không tồn tại!"));
        return reviewResponse(review, review.getCustomer().getUsername(), "Lấy đánh giá thành công!", false);
    }



    @Override
    @Transactional
    public ReviewResponse deleteReview(Long reviewId, String username) {
        Review review = checkDeleteReview(reviewId, username);

        try {
            review.setStatus(Status.INACTIVE);
            review.setUpdateAt(LocalDateTime.now());
            reviewRepository.save(review);
            return reviewResponse(review, username, "Xóa đánh giá thành công!", true);
        } catch (Exception e) {
            throw new IllegalArgumentException("Xóa đánh giá thất bại!");
        }
    }


    @Override
    public Review checkReviewRole(Long reviewId, String username, boolean isShop) {
        Review review = reviewRepository.findByReviewIdAndStatus(reviewId, Status.ACTIVE)
                .orElseThrow(() -> new IllegalArgumentException("Đánh giá không tồn tại"));

        if (isShop) {
            if (!review.getProduct().getCategory().getShop().getCustomer().getUsername().equals(username)) {
                throw new IllegalArgumentException("Bạn không phải chủ cửa hàng. Bạn không có quyền trả lời đánh giá này.");
            }
        } else {
            if (!review.getCustomer().getUsername().equals(username)) {
                throw new IllegalArgumentException("Bạn không phải chủ đánh giá. Bạn không có quyền trả lời đánh giá này.");
            }
        }

        return review;
    }



    @Override
    public Review checkReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Đánh giá không tồn tại!"));
        if (review.getStatus() == Status.INACTIVE) {
            throw new IllegalArgumentException("Đánh giá này đã bị xóa!");
        }

        return review;
    }

    private Review checkDeleteReview(Long reviewId, String username) {
        Review review = checkReview(reviewId);
        if (!review.getCustomer().getUsername().equals(username)) {
            throw new IllegalArgumentException("Bạn không có quyền xóa đánh giá này!");
        }
        return review;
    }




    private void checkOrderItem(Long orderItemId) {
        if (reviewRepository.existsByOrderItemOrderItemId(orderItemId)) {
            throw new IllegalArgumentException("Đã đánh giá sản phẩm này!");
        }
    }

    private void checkRoleReview(Long orderItemId, String username) {
        if (!orderItemRepository.existsByOrderItemIdAndCartCustomerUsername(orderItemId, username)) {
            throw new IllegalArgumentException("Bạn không có quyền đánh giá sản phẩm này!");
        }
    }


    private void checkOrderItemAndRoleReviewAndStatus(Long orderItemId, String username) {
        checkOrderItem(orderItemId);
        checkRoleReview(orderItemId, username);

        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new IllegalArgumentException("Sản phẩm không tồn tại!"));

        System.out.println("aaaaaaa"+orderItem.getCart().getStatus());

        if (orderItem.getCart().getStatus() == Status.DELIVERED ||
                orderItem.getCart().getStatus() == Status.COMPLETED) {
            return;
        }
        throw new IllegalArgumentException("Bạn không thể đánh giá sản phẩm này!");
    }


    private ReviewResponse reviewResponse(Review review, String username, String message, boolean created) {
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setReviewDTO(ReviewDTO.convertEntityToDTO(review));
        reviewResponse.setUsername(username);
        reviewResponse.setMessage(message);
        reviewResponse.setProductId(review.getProduct().getProductId());
        reviewResponse.setStatus(created ? "ok" : "success");
        reviewResponse.setCode(200);

        return reviewResponse;
    }

//    private ListReviewResponse listReviewResponse (List<Review> reviews, Long productId, String username, String message, boolean created){
//        ListReviewResponse listReviewResponse = new ListReviewResponse();
//        listReviewResponse.setReviewDTOs(ReviewDTO.convertEntitiesToDTOs(reviews));
//        listReviewResponse.setCount(reviews.size());
//        listReviewResponse.setProductId(productId);
//        listReviewResponse.setUsername(username);
//        listReviewResponse.setMessage(message);
//        listReviewResponse.setStatus(created ? "ok" : "success");
//        listReviewResponse.setCode(200);
//
//        return listReviewResponse;
//    }

}
