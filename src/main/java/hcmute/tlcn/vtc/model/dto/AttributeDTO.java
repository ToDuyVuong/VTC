package hcmute.tlcn.vtc.model.dto;


import hcmute.tlcn.vtc.model.entity.vtc.Attribute;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AttributeDTO {

    private Long attributeId;

    private String name;

    private String value;

    private boolean active;

    private Long shopId;

    public static List<AttributeDTO> convertToListDTO(List<Attribute> attributes){
        List<AttributeDTO> attributeDTOS = new ArrayList<>();
        for (Attribute attribute : attributes){
            ModelMapper modelMapper = new ModelMapper();
            AttributeDTO attributeDTO = modelMapper.map(attribute, AttributeDTO.class);
            attributeDTOS.add(attributeDTO);
        }
        return attributeDTOS;
    }
}
