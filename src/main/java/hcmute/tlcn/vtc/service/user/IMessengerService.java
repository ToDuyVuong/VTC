package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.data.user.UserSocket;
import hcmute.tlcn.vtc.model.data.user.request.MessengerRequest;
import hcmute.tlcn.vtc.model.data.user.response.MessengersResponse;

public interface IMessengerService {
    void saveMessenger(MessengerRequest request);

    MessengersResponse findAllByRomChatId(Long id, String username);

    UserSocket chatVsUser(UserSocket user);
}
