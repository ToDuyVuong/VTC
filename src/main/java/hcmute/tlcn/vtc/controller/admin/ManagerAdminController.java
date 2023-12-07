package hcmute.tlcn.vtc.controller.admin;

import hcmute.tlcn.vtc.model.data.manager.response.ManagerResponse;
import hcmute.tlcn.vtc.service.admin.IManagerAdminService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/manage")
@RequiredArgsConstructor
public class ManagerAdminController {

    @Autowired
    private IManagerAdminService managerAdminService;

    @PostMapping("/add-role-manager")
    public ResponseEntity<ManagerResponse> addRoleManager(@RequestParam  String usernameCustomer,
                                                          HttpServletRequest servletRequest) {
        String username = (String) servletRequest.getAttribute("username");
        ManagerResponse managerResponse = managerAdminService.addRoleManager(username, usernameCustomer.trim());
        return ResponseEntity.ok(managerResponse);
    }


}
