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

    private String image;

    private Status status;

    private String username;

    private Long orderItemId;

    private Date createdAt;

    private int countComment;

    private List<CommentDTO> commentDTOs;


    public static ReviewDTO convertEntityToDTO(Review review) {

        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setReviewId(review.getReviewId());
        reviewDTO.setContent(review.getContent());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setStatus(review.getStatus());
        reviewDTO.setImage(review.getImage());
        reviewDTO.setUsername(review.getCustomer().getUsername());
        reviewDTO.setOrderItemId(review.getOrderItem().getOrderItemId());
        reviewDTO.setCreatedAt(Date.from(review.getCreateAt().atZone(ZoneId.systemDefault()).toInstant()));
        if (review.getComments() != null) {
            reviewDTO.setCommentDTOs(CommentDTO.convertEntitiesToDTOs(review.getComments()));
        }
        reviewDTO.setCountComment(reviewDTO.getCommentDTOs().size());

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
