package hcmute.tlcn.vtc.model.data.dto;


import hcmute.tlcn.vtc.model.entity.Product;
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
public class ProductDTO {

    private Long productId;

    private String name;

    private String image;

    private String description;

    private String information;

    private Long sold;

    private Status status;

    private Long categoryId;

    private Long brandId;

    private List<ProductVariantDTO> productVariantDTOs;


    public static List<ProductDTO> convertToListDTO(List<Product> products) {
        List<ProductDTO> productDTOs = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        for (Product product : products) {
            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            productDTO.setProductVariantDTOs(ProductVariantDTO.convertToListDTO(product.getProductVariants()));
            productDTOs.add(productDTO);
        }
        return productDTOs;
    }

    public static ProductDTO convertEntityToDTO(Product product) {
        ModelMapper modelMapper = new ModelMapper();
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        productDTO.setProductVariantDTOs(ProductVariantDTO.convertToListDTO(product.getProductVariants()));
        return productDTO;
    }





}
