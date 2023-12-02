package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.entity.vtc.RomChat;
import org.springframework.transaction.annotation.Transactional;

public interface IRomChatService {
    @Transactional
    void saveRomChat(String sender, String receiver);

    RomChat findRomChat(String sender, String receiver);
}
