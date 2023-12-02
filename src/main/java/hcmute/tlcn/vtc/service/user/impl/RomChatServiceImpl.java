package hcmute.tlcn.vtc.service.user.impl;

import hcmute.tlcn.vtc.model.entity.vtc.RomChat;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.repository.RomChatRepository;
import hcmute.tlcn.vtc.service.user.IRomChatService;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class RomChatServiceImpl implements IRomChatService {

    @Autowired
    RomChatRepository romChatRepository;


    @Override
    @Transactional
    public void saveRomChat(String sender, String receiver) {
        if (!romChatRepository.existsBySenderAndReceiver(sender, receiver) && !romChatRepository.existsBySenderAndReceiver(receiver, sender)) {
            RomChat romChat = new RomChat();
            romChat.setSender(sender);
            romChat.setReceiver(receiver);
            romChat.setCreatedAt(LocalDateTime.now());
            romChat.setStatus(Status.ACTIVE);
           try {
                romChatRepository.save(romChat);
           }catch (Exception e){
               throw new NotFoundException("Không thể tạo rom chat");
           }
        }
        throw new NotFoundException("Đã tồn tại rom chat");
    }


    @Override
    public RomChat findRomChat(String sender, String receiver) {
       if (romChatRepository.existsBySenderAndReceiver(sender, receiver)) {
            return romChatRepository.findBySenderAndReceiver(sender, receiver)
                    .orElseThrow(() -> new NotFoundException("Không tìm thấy rom chat"));
        } else if (romChatRepository.existsBySenderAndReceiver(receiver, sender)) {
            return romChatRepository.findByReceiverAndSender(receiver, sender)
                    .orElseThrow(() -> new NotFoundException("Không tìm thấy rom chat"));
        }
        throw new NotFoundException("Không tìm thấy rom chat");
    }




}
