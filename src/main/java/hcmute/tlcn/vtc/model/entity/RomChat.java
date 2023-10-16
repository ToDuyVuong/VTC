package hcmute.tlcn.vtc.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RomChat {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long romChatId;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String receiver;

}
