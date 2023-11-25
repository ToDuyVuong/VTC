package hcmute.tlcn.vtc.controller.user;

import hcmute.tlcn.vtc.model.data.user.UserSocket;
import hcmute.tlcn.vtc.model.data.user.request.MessengerRequest;
import hcmute.tlcn.vtc.model.data.user.response.MessengersResponse;
import hcmute.tlcn.vtc.model.dto.MessengerDTO;
import hcmute.tlcn.vtc.service.user.IMessengerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    @Autowired
    private final IMessengerService messengerService;


    @MessageMapping("/chat.newUser")
    public void greeting(@Payload MessengerRequest request) {
        messengerService.saveMessenger(request);
    }

    @MessageMapping("/chatVsUser")
    public UserSocket chatVsUser(@Payload UserSocket user, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        user.setSender(username);
        return messengerService.chatVsUser(user);
    }

    //    @GetMapping("/api/messages/{romChatId}")
    @GetMapping("/history/{romChatId}")
    @ResponseBody
    public MessengersResponse getMessagesByRomChatId(@PathVariable Long romChatId, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        return messengerService.findAllByRomChatId(romChatId, username);
    }

    @GetMapping("/chat")
    public String chat() {
        return "socket";
    }

}
