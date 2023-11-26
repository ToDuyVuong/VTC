package hcmute.tlcn.vtc.model.data.user.request;


import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {

    private String content;

    private Long reviewId;

    private String username;

    private boolean isShop;

    public void validate() {

        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Nội dung không được để trống.");
        }

        if (reviewId == null) {
            throw new IllegalArgumentException("Mã review không được để trống.");
        }

        this.content = this.content.trim();
    }


}
