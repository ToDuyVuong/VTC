package hcmute.tlcn.vtc.model.data.vendor.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@RequiredArgsConstructor
public class StatisticsRequest {

    private Date startDate;
    private Date endDate;
    private String username;

    public void validate() {
        if (startDate == null) {
            throw new IllegalArgumentException("Ngày bắt đầu không được để trống.");
        }

        if (endDate == null) {
            throw new IllegalArgumentException("Ngày kết thúc không được để trống.");
        }

        if (startDate.after(endDate)) {
            throw new IllegalArgumentException("Ngày bắt đầu không được lớn hơn ngày kết thúc.");
        }

        //khoảng thời gian trong vòng 31
        if (endDate.getTime() - startDate.getTime() > 2678400000L) {
            throw new IllegalArgumentException("Khoảng thời gian không được lớn hơn 31 ngày.");
        }
    }



}
