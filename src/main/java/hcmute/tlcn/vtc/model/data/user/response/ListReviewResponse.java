package hcmute.tlcn.vtc.model.data.user.response;

import hcmute.tlcn.vtc.model.dto.ReviewDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListReviewResponse extends ResponseAbstract {

    private String username;
    private int count;
    private List<ReviewDTO> reviewDTOs;

}
