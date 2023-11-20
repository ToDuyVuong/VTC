package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.data.user.request.CommentRequest;
import hcmute.tlcn.vtc.model.data.user.response.CommentResponse;
import org.springframework.transaction.annotation.Transactional;

public interface ICommentCustomerService {
    CommentResponse addNewComment(CommentRequest request);

    @Transactional
    CommentResponse deleteComment(Long commentId, String username);
}
