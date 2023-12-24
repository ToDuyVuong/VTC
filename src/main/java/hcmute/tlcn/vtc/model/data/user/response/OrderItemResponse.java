package hcmute.tlcn.vtc.model.data.user.response;

import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponse extends ResponseAbstract {

    private Long orderItemId;
    private Long orderId;
    private Long cartId;
    private Long productVariantId;
    private String sku;
    private String productVariantImage;
    private Long price;
    private int quantity;
    private Long productId;
    private String productName;
    private String productImage;
    private Long shopId;
    private String shopName;
}
