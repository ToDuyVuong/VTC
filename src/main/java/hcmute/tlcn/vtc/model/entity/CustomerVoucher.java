package hcmute.tlcn.vtc.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CustomerVoucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerVoucherId;

    private boolean used;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;
}