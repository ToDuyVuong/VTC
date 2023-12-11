package hcmute.tlcn.vtc.vnpay;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO implements Serializable {
    private String status;
    private String message;
    private String URL;
}
