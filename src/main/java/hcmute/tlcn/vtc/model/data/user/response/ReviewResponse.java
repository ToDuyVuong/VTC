package hcmute.tlcn.vtc.model.data.user.response;

import hcmute.tlcn.vtc.model.dto.ReviewDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse extends ResponseAbstract {

    private String username;
    private Long productId;
    private ReviewDTO reviewDTO;



}
