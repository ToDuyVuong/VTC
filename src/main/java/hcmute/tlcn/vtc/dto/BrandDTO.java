package hcmute.tlcn.vtc.dto;


import hcmute.tlcn.vtc.model.extra.entity.Brand;
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
public class BrandDTO {

    private Long brandId;

    private String name;

    private String image;

    private String description;

    private String information;

    private String origin;

    private Status status;


    public void validate(){
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Tên thương hiệu không được để trống.");
        }
        if(description == null || description.isEmpty()){
            throw new IllegalArgumentException("Mô tả không được để trống.");
        }
        if(information == null || information.isEmpty()){
            throw new IllegalArgumentException("Thông tin không được để trống.");
        }
        if(origin == null || origin.isEmpty()){
            throw new IllegalArgumentException("Xuất xứ không được để trống.");
        }
        if (image == null || image.isEmpty()) {
            throw new IllegalArgumentException("Hình ảnh không được để trống.");
        }
        if (status == null) {
            throw new IllegalArgumentException("Trạng thái không được để trống.");
        }
    }

    public static List<BrandDTO> convertToListDTO(List<Brand> brands){
        List<BrandDTO> brandDTOS = new ArrayList<>();
        for(Brand brand : brands){
            ModelMapper modelMapper = new ModelMapper();
            BrandDTO brandDTO = modelMapper.map(brand, BrandDTO.class);
            brandDTOS.add(brandDTO);
        }
        return brandDTOS;
    }
}
