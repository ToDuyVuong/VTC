package hcmute.tlcn.vtc.model.dto;


import hcmute.tlcn.vtc.model.extra.Status;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AttributeDTO {

    private Long attributeId;

    private String name;

    private String value;

    private Status status;

    private Long shopId;
}
