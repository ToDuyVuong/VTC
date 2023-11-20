package hcmute.tlcn.vtc.model.dto;

import hcmute.tlcn.vtc.model.entity.Comment;
import hcmute.tlcn.vtc.model.entity.Review;
import hcmute.tlcn.vtc.model.extra.Status;
import lombok.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long commentId;

    private String content;

    private Status status;

    private Date createDate;

    private String username;

    private String shopName;


    public static CommentDTO convertEntityToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentId(comment.getCommentId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setStatus(comment.getStatus());
        commentDTO.setCreateDate(comment.getCreateDate());
        commentDTO.setUsername(comment.getCustomer().getUsername());
        if (comment.getShopName() != null) {
            commentDTO.setShopName(comment.getShopName());
        }
//        commentDTO.setShopName(comment.getShopName().isEmpty() ? null : comment.getShopName());
        return commentDTO;
    }


    public static List<CommentDTO> convertEntitiesToDTOs(List<Comment> comments) {
        List<CommentDTO> commentDTOs = new ArrayList<>();
//        comments.forEach(comment -> commentDTOs.add(convertEntityToDTO(comment)));
        for (Comment comment : comments) {
            if (comment.getStatus() == Status.ACTIVE) {
                commentDTOs.add(convertEntityToDTO(comment));
            }
        }
        commentDTOs.sort(Comparator.comparing(CommentDTO::getCreateDate));

        return commentDTOs;
    }

}
