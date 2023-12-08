package hcmute.tlcn.vtc.model.dto;


import hcmute.tlcn.vtc.model.entity.vtc.Product;
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
        //ModelMapper modelMapper = new ModelMapper();

        for (Product product : products) {
            //ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            ProductDTO productDTO = convertEntityToDTO(product);

           // productDTO.setProductVariantDTOs(ProductVariantDTO.convertToListDTO(product.getProductVariants()));
            productDTOs.add(productDTO);
        }
        return productDTOs;
    }

    public static ProductDTO convertEntityToDTO(Product product) {
        //ModelMapper modelMapper = new ModelMapper();
        //ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setName(product.getName());
        productDTO.setImage(product.getImage());
        productDTO.setDescription(product.getDescription());
        productDTO.setInformation(product.getInformation());
        productDTO.setSold(product.getSold());
        productDTO.setStatus(product.getStatus());
        productDTO.setCategoryId(product.getCategory().getCategoryId());
        productDTO.setBrandId(product.getBrand() != null ? product.getBrand().getBrandId() : null);
        productDTO.setProductVariantDTOs(ProductVariantDTO.convertToListDTO(product.getProductVariants()));
        return productDTO;
    }





}
