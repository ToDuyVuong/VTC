package hcmute.tlcn.vtc.controller.user;

import hcmute.tlcn.vtc.model.data.user.request.ReviewRequest;
import hcmute.tlcn.vtc.model.data.user.response.ReviewResponse;
import hcmute.tlcn.vtc.repository.ReviewRepository;
import hcmute.tlcn.vtc.service.user.IReviewCustomerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer/review")
@RequiredArgsConstructor
public class ReviewCustomerController {

    @Autowired
    private IReviewCustomerService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping("/add")
    public ResponseEntity<ReviewResponse> addNewReview(@RequestBody ReviewRequest reviewRequest,
                                                       HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        reviewRequest.validate();



        return ResponseEntity.ok(reviewService.addNewReview(reviewRequest, username));
    }


    @GetMapping("/detail/by-order-item/{orderItemId}")
    public ResponseEntity<ReviewResponse> getReviewDetailByOrderItemId(@PathVariable Long orderItemId) {
        if (orderItemId == null) {
            throw new IllegalArgumentException("Mã đánh giá không được để trống!");
        }
        return ResponseEntity.ok(reviewService.getReviewByOrderItemId(orderItemId));
    }


    @PatchMapping("/delete/{reviewId}")
    public ResponseEntity<ReviewResponse> deleteReview(@PathVariable Long reviewId,
                                                       HttpServletRequest request) {
        if (reviewId == null) {
            throw new IllegalArgumentException("Mã đánh giá không được để trống!");
        }
        String username = (String) request.getAttribute("username");
        return ResponseEntity.ok(reviewService.deleteReview(reviewId, username));
    }

    @GetMapping("exist/{orderItemId}")
    public ResponseEntity<Boolean> checkReviewExist(@PathVariable Long orderItemId) {
        if (orderItemId == null) {
            throw new IllegalArgumentException("Mã đánh giá không được để trống!");
        }
        return ResponseEntity.ok(reviewRepository.existsByOrderItemOrderItemId(orderItemId));
    }


}
