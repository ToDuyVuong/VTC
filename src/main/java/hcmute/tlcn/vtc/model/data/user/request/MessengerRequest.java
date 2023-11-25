package hcmute.tlcn.vtc.model.data.user.request;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessengerRequest {

    private String content;
    private String sender;
    private String receiver;
}
