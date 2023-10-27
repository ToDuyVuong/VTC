package hcmute.tlcn.vtc.controller.admin;

import hcmute.tlcn.vtc.model.data.admin.request.VoucherAdminRequest;
import hcmute.tlcn.vtc.model.data.admin.response.VoucherAdminResponse;
import hcmute.tlcn.vtc.service.admin.IVoucherAdminService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/voucher")
@RequiredArgsConstructor
public class VoucherAdminController {

    @Autowired
    private IVoucherAdminService voucherAdminService;


    @PostMapping("/add")
    public ResponseEntity<VoucherAdminResponse> addNewVoucherAdmin(@RequestBody VoucherAdminRequest request,
                                                                   HttpServletRequest servletRequest) {
        String username = (String) servletRequest.getAttribute("username");
        request.setUsername(username);
        request.validateCreate();

        return ResponseEntity.ok(voucherAdminService.addNewVoucherAdmin(request));

    }
}
