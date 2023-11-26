package hcmute.tlcn.vtc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class StatisticsDTO {

    private Double totalMoney;
    private int totalOrder;
    private int totalProduct;
    private Date date;

}
