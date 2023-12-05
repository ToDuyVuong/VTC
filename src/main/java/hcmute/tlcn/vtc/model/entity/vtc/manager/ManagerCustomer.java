package hcmute.tlcn.vtc.model.entity.vtc.manager;

import hcmute.tlcn.vtc.model.entity.vtc.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ManagerCustomer {



    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long managerCustomerId;

    private String note;

    private boolean isLock;

    private boolean isDelete;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id")
    private Customer manager;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
