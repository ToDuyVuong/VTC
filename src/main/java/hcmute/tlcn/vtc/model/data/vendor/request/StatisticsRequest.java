package hcmute.tlcn.vtc.model.data.vendor.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@RequiredArgsConstructor
public class StatisticsRequest {

    private Date dateStart;
    private Date dateEnd;
    private String username;

    public void validate() {
        if (this.dateStart == null) {
            throw new IllegalArgumentException("Ngày bắt đầu không được để trống!");
        }

        if (this.dateEnd == null) {
            throw new IllegalArgumentException("Ngày kết thúc không được để trống!");
        }

        if(this.dateStart.after(this.dateEnd)) {
            throw new IllegalArgumentException("Ngày bắt đầu không được lớn hơn ngày kết thúc!");
        }
    }

}
