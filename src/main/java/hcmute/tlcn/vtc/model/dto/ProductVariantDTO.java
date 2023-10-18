package hcmute.tlcn.vtc.model.dto;

import hcmute.tlcn.vtc.model.entity.Product;
import hcmute.tlcn.vtc.model.extra.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductVariantDTO {

    private Long productVariantId;

    private String sku;

    private String image;

    private Long price;

    private Long quantity;

    private Status status;

    private Long productId;

    private List<AttributeDTO> attributeDTOs ;






}
