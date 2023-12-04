package hcmute.tlcn.vtc.model.data.paging.request;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductPageRequest {

    private int page;
    private int size;
    private Long categoryId;


//    private String sort;
//    private String search;
//    private Long brandId;
//    private Long priceFrom;
//    private Long priceTo;
//    private Long shopId;
//    private Long userId;
//    private Long voucherId;
//    private Long productId;
//    private Long reviewId;
//    private Long commentId;

    public static void validate(ProductPageRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Yêu cầu không được để trống!");
        }
        if (request.getPage() < 0) {
            throw new IllegalArgumentException("Trang không hợp lệ!");
        }
        if (request.getSize() < 0) {
            throw new IllegalArgumentException("Kích thước trang không hợp lệ!");
        }
    }


}
