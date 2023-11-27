package hcmute.tlcn.vtc.model.data.vendor.response;

import hcmute.tlcn.vtc.model.dto.StatisticsDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsResponse extends ResponseAbstract {

    private int count;
    private int totalOrder;
    private String username;
    private Date dateStart;
    private Date dateEnd;
    private List<StatisticsDTO> statisticsDTOs;

}
