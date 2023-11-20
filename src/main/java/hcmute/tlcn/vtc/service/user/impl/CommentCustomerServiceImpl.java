package hcmute.tlcn.vtc.service.user.impl;

import hcmute.tlcn.vtc.model.data.guest.ListCommentResponse;
import hcmute.tlcn.vtc.model.data.user.request.CommentRequest;
import hcmute.tlcn.vtc.model.data.user.response.CommentResponse;
import hcmute.tlcn.vtc.model.dto.CommentDTO;
import hcmute.tlcn.vtc.model.entity.Comment;
import hcmute.tlcn.vtc.model.entity.Customer;
import hcmute.tlcn.vtc.model.entity.Review;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.repository.CommentRepository;
import hcmute.tlcn.vtc.repository.ReviewRepository;
import hcmute.tlcn.vtc.service.user.ICommentCustomerService;
import hcmute.tlcn.vtc.service.user.ICustomerService;
import hcmute.tlcn.vtc.service.user.IReviewCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentCustomerServiceImpl implements ICommentCustomerService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private final IReviewCustomerService reviewCustomerService;


    @Override
    @Transactional
    public CommentResponse addNewComment(CommentRequest request) {
        Customer customer = customerService.getCustomerByUsername(request.getUsername());
        Review review = reviewCustomerService.checkReviewRole(request.getReviewId(), request.getUsername(), request.isShop());
        String shopName = request.isShop() ? review.getProduct().getCategory().getShop().getName() : "";

        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setReview(review);
        comment.setCustomer(customer);
        comment.setStatus(Status.ACTIVE);
        comment.setShopName(shopName);
        comment.setCreateDate(new Date());

        try {
            commentRepository.save(comment);
            return commentResponse(comment, request.getReviewId(), request.getUsername(), "Bình luận thành công vào đánh giá thành công.");
        } catch (Exception e) {
            throw new IllegalArgumentException("Bình luận thất bại.");
        }
    }


    @Override
    @Transactional
    public CommentResponse deleteComment(Long commentId, String username) {
        Comment comment = checkComment(commentId, username);

        comment.setStatus(Status.INACTIVE);
        try {
            commentRepository.save(comment);
            return commentResponse(comment, comment.getReview().getReviewId(), username, "Xóa bình luận thành công.");
        } catch (Exception e) {
            throw new IllegalArgumentException("Xóa bình luận thất bại." + e.getMessage());
        }
    }





    private Comment checkComment(Long commentId, String username) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Bình luận không tồn tại."));

        if (!comment.getCustomer().getUsername().equals(username)) {
            throw new IllegalArgumentException("Bạn không phải chủ bình luận. Bạn không có quyền xóa bình luận này.");
        }

        if (comment.getStatus() == Status.INACTIVE) {
            throw new IllegalArgumentException("Bình luận này đã bị xóa.");
        }

        return comment;
    }





    private CommentResponse commentResponse(Comment comment, Long reviewId, String username, String message) {
        CommentResponse response = new CommentResponse();
        response.setCommentDTO(CommentDTO.convertEntityToDTO(comment));
        response.setMessage(message);
        response.setCode(200);
        response.setUsername(username);
        response.setReviewId(reviewId);
        response.setStatus("success");
        return response;
    }



}
