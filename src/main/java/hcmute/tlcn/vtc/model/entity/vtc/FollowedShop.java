package hcmute.tlcn.vtc.model.entity.vtc;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FollowedShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followedShopId;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Shop shop;

    private LocalDateTime createAt;
}
