package hcmute.tlcn.vtc.entity;

import hcmute.tlcn.vtc.entity.extra.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Messenger {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messengerId;

    private String content;

    private Status status;

    private String time;

    @PrePersist
    public void prePersist() {
        // Set the time property with the current timestamp when a new entity is persisted.
        this.time = LocalDateTime.now().toString();
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer  customer;

    @ManyToOne
    @JoinColumn(name = "romchat_id")
    private RomChat romChat;








}
