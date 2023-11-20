package hcmute.tlcn.vtc.service.guest;

import hcmute.tlcn.vtc.model.data.user.response.ListReviewResponse;

public interface IReviewService {
    ListReviewResponse getReviewsByProductId(Long productId);
}
