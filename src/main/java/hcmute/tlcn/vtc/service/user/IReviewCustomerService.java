package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.data.user.request.ReviewRequest;
import hcmute.tlcn.vtc.model.data.user.response.ReviewResponse;
import hcmute.tlcn.vtc.model.entity.Review;

public interface IReviewCustomerService {
    ReviewResponse addNewReview(ReviewRequest request, String username);

    ReviewResponse deleteReview(Long reviewId, String username);


    Review checkReviewRole(Long reviewId, String username, boolean isShop);

    Review checkReview(Long reviewId);
}
