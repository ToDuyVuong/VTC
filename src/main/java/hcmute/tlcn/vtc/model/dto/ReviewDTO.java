package hcmute.tlcn.vtc.model.dto;

import hcmute.tlcn.vtc.model.entity.Review;
import hcmute.tlcn.vtc.model.extra.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class ReviewDTO {

    private Long reviewId;

    private String content;

    private int rating;

    private Status status;

    private String username;

    private List<Long> commentIds;

    private Long orderItemId;

    private Date createdAt;


    public static ReviewDTO convertEntityToDTO(Review review) {




        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setReviewId(review.getReviewId());
        reviewDTO.setContent(review.getContent());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setStatus(review.getStatus());
        reviewDTO.setUsername(review.getCustomer().getUsername());
        reviewDTO.setOrderItemId(review.getOrderItem().getOrderItemId());
        reviewDTO.setCreatedAt(Date.from(review.getCreateAt().atZone(ZoneId.systemDefault()).toInstant()));
        return reviewDTO;
    }


    public static List<ReviewDTO> convertEntitiesToDTOs(List<Review> reviews) {
        List<ReviewDTO> reviewDTOs = new ArrayList<>();
        for (Review review : reviews) {
            reviewDTOs.add(convertEntityToDTO(review));
        }
        reviewDTOs.sort(Comparator.comparing(ReviewDTO::getCreatedAt).reversed());
        return reviewDTOs;
    }

}
