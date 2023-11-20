package hcmute.tlcn.vtc.service.guest;

import hcmute.tlcn.vtc.model.data.guest.ListReviewResponse;
import hcmute.tlcn.vtc.model.data.user.response.ReviewResponse;

public interface IReviewService {
    ReviewResponse getReviewDetailById(Long reviewId);

    ListReviewResponse getReviewsByProductId(Long productId);

    ListReviewResponse getReviewsByProductIdAndRating(Long productId, int rating);

    ListReviewResponse getReviewsByProductIdAndImageNotNull(Long productId);
}
