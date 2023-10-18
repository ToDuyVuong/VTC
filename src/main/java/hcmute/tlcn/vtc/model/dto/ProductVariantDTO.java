package hcmute.tlcn.vtc.model.dto;

import hcmute.tlcn.vtc.model.entity.Product;
import hcmute.tlcn.vtc.model.entity.ProductVariant;
import hcmute.tlcn.vtc.model.extra.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.modelmapper.ModelMapper;

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

    private List<AttributeDTO> attributeDTOs;


    public static List<ProductVariantDTO> convertToListDTO(List<ProductVariant> productVariants) {
        List<ProductVariantDTO> productVariantDTOs = new ArrayList<>();

        for (ProductVariant productVariant : productVariants) {

            ProductVariantDTO productVariantDTO = new ProductVariantDTO();
            productVariantDTO.setProductVariantId(productVariant.getProductVariantId());
            productVariantDTO.setSku(productVariant.getSku());
            productVariantDTO.setImage(productVariant.getImage());
            productVariantDTO.setPrice(productVariant.getPrice());
            productVariantDTO.setQuantity(productVariant.getQuantity());
            productVariantDTO.setStatus(productVariant.getStatus());
            productVariantDTO.setAttributeDTOs(AttributeDTO.convertToListDTO(productVariant.getAttributes()));
            productVariantDTO.setProductId(productVariant.getProduct().getProductId());

            productVariantDTOs.add(productVariantDTO);
        }
        return productVariantDTOs;
    }


}
