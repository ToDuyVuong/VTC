package hcmute.tlcn.vtc.model.dto;

import hcmute.tlcn.vtc.model.entity.vtc.ProductVariant;
import hcmute.tlcn.vtc.model.extra.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
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

    private int quantity;

    private Status status;

    private Long productId;

    private String productName;

    private String productImage;

    private List<AttributeDTO> attributeDTOs;



    public static ProductVariantDTO convertEntityToDTO(ProductVariant productVariant) {
        ProductVariantDTO productVariantDTO = new ProductVariantDTO();
        productVariantDTO.setProductVariantId(productVariant.getProductVariantId());
        productVariantDTO.setSku(productVariant.getSku());
        productVariantDTO.setImage(productVariant.getImage());
        productVariantDTO.setPrice(productVariant.getPrice());
        productVariantDTO.setQuantity(productVariant.getQuantity());
        productVariantDTO.setStatus(productVariant.getStatus());
        productVariantDTO.setAttributeDTOs(AttributeDTO.convertToListDTO(productVariant.getAttributes()));
        productVariantDTO.setProductId(productVariant.getProduct().getProductId());
        productVariantDTO.setProductName(productVariant.getProduct().getName());
        productVariantDTO.setProductImage(productVariant.getProduct().getImage());

        return productVariantDTO;
    }


    public static List<ProductVariantDTO> convertToListDTO(List<ProductVariant> productVariants) {
        List<ProductVariantDTO> productVariantDTOs = new ArrayList<>();

        for (ProductVariant productVariant : productVariants) {

            ProductVariantDTO productVariantDTO = convertEntityToDTO(productVariant);
//            ProductVariantDTO productVariantDTO = new ProductVariantDTO();
//            productVariantDTO.setProductVariantId(productVariant.getProductVariantId());
//            productVariantDTO.setSku(productVariant.getSku());
//            productVariantDTO.setImage(productVariant.getImage());
//            productVariantDTO.setPrice(productVariant.getPrice());
//            productVariantDTO.setQuantity(productVariant.getQuantity());
//            productVariantDTO.setStatus(productVariant.getStatus());
//            productVariantDTO.setAttributeDTOs(AttributeDTO.convertToListDTO(productVariant.getAttributes()));
//            productVariantDTO.setProductId(productVariant.getProduct().getProductId());

            productVariantDTOs.add(productVariantDTO);
        }
        return productVariantDTOs;
    }


}
