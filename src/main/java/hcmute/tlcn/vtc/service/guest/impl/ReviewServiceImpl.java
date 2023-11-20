package hcmute.tlcn.vtc.service.guest.impl;

import hcmute.tlcn.vtc.model.data.user.response.ListReviewResponse;
import hcmute.tlcn.vtc.model.dto.ReviewDTO;
import hcmute.tlcn.vtc.model.entity.Review;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.repository.ReviewRepository;
import hcmute.tlcn.vtc.service.guest.IReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements IReviewService {

    @Autowired
    private final ReviewRepository reviewRepository;


    @Override
    public ListReviewResponse getReviewsByProductId(Long productId) {

        List<Review> reviews = reviewRepository.findAllByProductProductIdAndStatus(productId, Status.ACTIVE)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đánh giá nào!"));

        return listReviewResponse(reviews,"Lấy danh sách đánh giá thành công!", productId);
    }


    @Override
    public ListReviewResponse getReviewsByProductIdAndRating(Long productId, int rating) {

        List<Review> reviews = reviewRepository.findAllByProductProductIdAndRatingAndStatus(productId, rating, Status.ACTIVE)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đánh giá nào!"));

        return listReviewResponse(reviews,"Lấy danh sách đánh giá theo xếp hạng thành công!", productId);
    }

    private long averageRating(List<Review> reviews){
        long sum = 0;
        for (Review review : reviews){
            sum += review.getRating();
        }
        return !reviews.isEmpty()  ? sum / reviews.size() : 0;
    }

    private ListReviewResponse listReviewResponse (List<Review> reviews, String message, Long productId){
        ListReviewResponse listReviewResponse = new ListReviewResponse();
        listReviewResponse.setReviewDTOs(ReviewDTO.convertEntitiesToDTOs(reviews));
        listReviewResponse.setCount(reviews.size());
        listReviewResponse.setProductId(productId);
        listReviewResponse.setMessage(message);
        listReviewResponse.setStatus("OK");
        listReviewResponse.setCode(200);
        listReviewResponse.setAverageRating(averageRating(reviews));

        return listReviewResponse;
    }

}
