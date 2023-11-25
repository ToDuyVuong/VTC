package hcmute.tlcn.vtc.model.dto;


import hcmute.tlcn.vtc.model.entity.Messenger;
import hcmute.tlcn.vtc.model.extra.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class MessengerDTO {

    private Long messengerId;

    private String content;

    private Status status;

    private Date date;

    private Long romChatId;

    private String usernameSender;

    private String usernameReceiver;


    public static MessengerDTO convertEntityToDTO(Messenger messenger) {
        MessengerDTO messengerDTO = new MessengerDTO();
        messengerDTO.setMessengerId(messenger.getMessengerId());
        messengerDTO.setContent(messenger.getContent());
        messengerDTO.setStatus(messenger.getStatus());
        messengerDTO.setDate(messenger.getDate());
        messengerDTO.setRomChatId(messenger.getRomChat().getRomChatId());
        messengerDTO.setUsernameSender(messenger.getCustomer().getUsername());
        messengerDTO.setUsernameReceiver(messenger.getRomChat().getReceiver());
        return messengerDTO;
    }


    public static List<MessengerDTO> convertEntitiesToDTOs(List<Messenger> messengers) {
        List<MessengerDTO> messengerDTOs = new ArrayList<>();
        for (Messenger messenger : messengers) {
            messengerDTOs.add(convertEntityToDTO(messenger));
        }
        messengerDTOs.sort(Comparator.comparing(MessengerDTO::getDate, Comparator.reverseOrder()));
        return messengerDTOs;
    }

}
