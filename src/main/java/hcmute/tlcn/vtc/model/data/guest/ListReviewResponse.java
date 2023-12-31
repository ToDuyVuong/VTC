package hcmute.tlcn.vtc.model.data.guest;

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

    private int count;
    private Long productId;
    private long averageRating;
    private List<ReviewDTO> reviewDTOs;

}
