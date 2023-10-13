package hcmute.tlcn.vtc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hcmute.tlcn.vtc.entity.extra.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String name;

    private String image;

    private boolean adminOnly;

//    public void setAdminOnly(boolean isAdminOnly) {
//        this.adminOnly = isAdminOnly;
//    }

//    @Enumerated(EnumType.STRING)
//    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime atCreate;

    private LocalDateTime atUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = true)
    @JsonIgnore
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = true)
    @JsonIgnore
    private Category parent;


}
