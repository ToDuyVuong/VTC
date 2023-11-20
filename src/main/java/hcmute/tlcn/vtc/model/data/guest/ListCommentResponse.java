package hcmute.tlcn.vtc.model.data.guest;

import hcmute.tlcn.vtc.model.dto.CommentDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListCommentResponse extends ResponseAbstract {

    private int count;
    private Long reviewId;
    private List<CommentDTO> commentDTOs;
}
