package hcmute.tlcn.vtc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hcmute.tlcn.vtc.entity.Category;
import hcmute.tlcn.vtc.entity.Shop;
import hcmute.tlcn.vtc.entity.extra.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Long categoryId;

    private String name;

    private String image;

    private boolean adminOnly;

    private Status status;

    private LocalDateTime atUpdate;

    private Shop shop;

    private Category parent;

}
