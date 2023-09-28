package hcmute.tlcn.vtc.dto;

import hcmute.tlcn.vtc.entity.extra.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {


    private Long adminId;

    private String username;

    private String email;

    private String phone;




}
