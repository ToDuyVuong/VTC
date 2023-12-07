package hcmute.tlcn.vtc.service.admin;

import hcmute.tlcn.vtc.model.data.manager.response.ManagerResponse;
import org.springframework.transaction.annotation.Transactional;

public interface IManagerAdminService {
    @Transactional
    ManagerResponse addRoleManager(String username, String usernameCustomer);
}
