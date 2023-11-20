package hcmute.tlcn.vtc.model.data.user.response;

import hcmute.tlcn.vtc.model.dto.CommentDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse extends ResponseAbstract {

    private String username;
    private Long reviewId;
    private CommentDTO commentDTO;
}
