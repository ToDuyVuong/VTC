package hcmute.tlcn.vtc.service.guest.impl;

import hcmute.tlcn.vtc.model.data.guest.ListCommentResponse;
import hcmute.tlcn.vtc.model.dto.CommentDTO;
import hcmute.tlcn.vtc.model.entity.vtc.Comment;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.repository.CommentRepository;
import hcmute.tlcn.vtc.repository.ReviewRepository;
import hcmute.tlcn.vtc.service.guest.ICommentService;
import hcmute.tlcn.vtc.service.user.IReviewCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private final IReviewCustomerService reviewCustomerService;


    @Override
    public List<CommentDTO> getListCommentDTO(Long reviewId) {
        reviewCustomerService.checkReview(reviewId);

        List<Comment> comments = commentRepository.findAllByReviewReviewIdAndStatus(reviewId, Status.ACTIVE)
                .orElseThrow(() -> new IllegalArgumentException("Không có bình luận nào cho đánh giá này."));

//        System.out.println("CommentDTO.convertEntitiesToDTOs(comments) = " + CommentDTO.convertEntitiesToDTOs(comments));


        return CommentDTO.convertEntitiesToDTOs(comments);
    }

    @Override
    public ListCommentResponse getComments(Long reviewId) {
        reviewCustomerService.checkReview(reviewId);

        List<Comment> comments = commentRepository.findAllByReviewReviewIdAndStatus(reviewId, Status.ACTIVE)
                .orElseThrow(() -> new IllegalArgumentException("Không có bình luận nào cho đánh giá này."));

        return listCommentResponse(comments, reviewId);
    }


    private ListCommentResponse listCommentResponse(List<Comment> comments, Long reviewId) {
        ListCommentResponse response = new ListCommentResponse();
        response.setCommentDTOs(CommentDTO.convertEntitiesToDTOs(comments));

        System.out.println("CommentDTO.convertEntitiesToDTOs(comments) = " + CommentDTO.convertEntitiesToDTOs(comments));

        response.setCount(comments.size());
        System.out.println("comments.size() = " + comments.size());
        response.setReviewId(reviewId);
        System.out.println("reviewId = " + reviewId);
        response.setMessage("Lấy danh sách bình luận của đánh giá thành công.");
        response.setCode(200);
        response.setStatus("OK");
        return response;
    }




}
