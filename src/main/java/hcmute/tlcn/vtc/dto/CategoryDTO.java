package hcmute.tlcn.vtc.dto;

import hcmute.tlcn.vtc.model.extra.entity.Category;
import hcmute.tlcn.vtc.model.extra.entity.Shop;
import hcmute.tlcn.vtc.model.extra.Status;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Long categoryId;

    private String name;

    private String image;

    private String description;

    private boolean adminOnly;

    private Status status;

    private Shop shop;

    private Category parent;

    public static List<CategoryDTO> convertToListDTO(List<Category> categories) {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category category : categories) {
            ModelMapper modelMapper = new ModelMapper();
            CategoryDTO addressDTO = modelMapper.map(category, CategoryDTO.class);
            categoryDTOS.add(addressDTO);
        }
        return categoryDTOS;
    }

}
