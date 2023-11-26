package hcmute.tlcn.vtc.model.data.user.request;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {

    private String content;

    private int rating;

    private Long orderItemId;

    private String image;

    public void validate() {

        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Nội dung không được để trống.");
        }

        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Đánh giá không hợp lệ.");
        }

        if (orderItemId == null) {
            throw new IllegalArgumentException("Đơn hàng không được để trống.");
        }

        this.content = this.content.trim();
        this.image = this.image.trim();

    }


}
