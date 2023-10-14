package hcmute.tlcn.vtc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hcmute.tlcn.vtc.entity.Address;
import hcmute.tlcn.vtc.entity.Category;
import hcmute.tlcn.vtc.entity.Shop;
import hcmute.tlcn.vtc.entity.extra.Status;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
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
