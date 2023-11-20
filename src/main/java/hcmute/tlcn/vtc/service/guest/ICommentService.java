package hcmute.tlcn.vtc.service.guest;

import hcmute.tlcn.vtc.model.data.guest.ListCommentResponse;
import hcmute.tlcn.vtc.model.dto.CommentDTO;

import java.util.List;

public interface ICommentService {

    List<CommentDTO> getListCommentDTO(Long reviewId);

    ListCommentResponse getComments(Long reviewId);
}
