package hcmute.tlcn.vtc.controller.guest;

import hcmute.tlcn.vtc.model.data.guest.ListCommentResponse;
import hcmute.tlcn.vtc.service.guest.ICommentService;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    @Autowired
    private ICommentService commentService;


    @GetMapping("/get/{reviewId}")
    public ResponseEntity<ListCommentResponse> getComments(@PathVariable Long reviewId) {
        if (reviewId == null) {
            throw new NotFoundException("Mã đánh giá không được để trống!");
        }
        return ResponseEntity.ok(commentService.getComments(reviewId));
    }


}
