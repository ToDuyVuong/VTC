package hcmute.tlcn.vtc.model.data.user.response;

import hcmute.tlcn.vtc.model.dto.MessengerDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessengersResponse  extends ResponseAbstract {

    private String username;

    private int count;

    private Long romChatId;

    private List<MessengerDTO> messengerDTOs;
}
