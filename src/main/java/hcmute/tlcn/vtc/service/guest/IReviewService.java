package hcmute.tlcn.vtc.service.guest;

import hcmute.tlcn.vtc.model.data.guest.ListReviewResponse;

public interface IReviewService {
    ListReviewResponse getReviewsByProductId(Long productId);

    ListReviewResponse getReviewsByProductIdAndRating(Long productId, int rating);

    ListReviewResponse getReviewsByProductIdAndImageNotNull(Long productId);
}
