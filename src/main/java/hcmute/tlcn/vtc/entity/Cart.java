package hcmute.tlcn.vtc.entity;

import hcmute.tlcn.vtc.entity.extra.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private OffsetDateTime atCreate;

    private OffsetDateTime atUpdate;
}
