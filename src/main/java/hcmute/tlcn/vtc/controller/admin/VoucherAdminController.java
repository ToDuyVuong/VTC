package hcmute.tlcn.vtc.controller.admin;

import hcmute.tlcn.vtc.model.data.admin.request.VoucherAdminRequest;
import hcmute.tlcn.vtc.model.data.admin.response.ListVoucherAdminResponse;
import hcmute.tlcn.vtc.model.data.admin.response.VoucherAdminResponse;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.model.extra.VoucherType;
import hcmute.tlcn.vtc.service.admin.IVoucherAdminService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/detail/system/{voucherId}")
    public ResponseEntity<VoucherAdminResponse> getVoucherAdminByVoucherId(@PathVariable Long voucherId) {
        if (voucherId == null) {
            throw new IllegalArgumentException("Mã của mã giảm giá không được để trống!");
        }
        return ResponseEntity.ok(voucherAdminService.getVoucherAdminByVoucherId(voucherId));
    }


    @GetMapping("/get-all/system/by-username")
    public ResponseEntity<ListVoucherAdminResponse> getAllVoucherAdminByUsername(HttpServletRequest servletRequest) {
        String username = (String) servletRequest.getAttribute("username");

        return ResponseEntity.ok(voucherAdminService.getListVoucherAdminByUsername(username));
    }


    @GetMapping("/get-all/system")
    public ResponseEntity<ListVoucherAdminResponse> getAllVoucherAdmin(HttpServletRequest servletRequest) {
        String username = (String) servletRequest.getAttribute("username");
        return ResponseEntity.ok(voucherAdminService.getListVoucherAdmin(username));
    }


    @GetMapping("/get-all/system/by-status")
    public ResponseEntity<ListVoucherAdminResponse> getAllVoucherAdminByStatus(@RequestParam Status status,
                                                                               HttpServletRequest servletRequest) {
        String username = (String) servletRequest.getAttribute("username");
        return ResponseEntity.ok(voucherAdminService.getListVoucherAdminByStatus(username, status));
    }


    @GetMapping("/get-all/system/by-type")
    public ResponseEntity<ListVoucherAdminResponse> getAllVoucherAdminByType(@RequestParam VoucherType type,
                                                                             HttpServletRequest servletRequest) {
        String username = (String) servletRequest.getAttribute("username");
        return ResponseEntity.ok(voucherAdminService.getListVoucherAdminByType(username, type));
    }

    @PutMapping("/update")
    public ResponseEntity<VoucherAdminResponse> updateVoucherAdmin(@RequestBody VoucherAdminRequest request,
                                                                   HttpServletRequest servletRequest) {
        String username = (String) servletRequest.getAttribute("username");
        request.setUsername(username);
        request.validateUpdate();

        return ResponseEntity.ok(voucherAdminService.updateVoucherAdmin(request, username));
    }


    @PatchMapping("/update-status/{voucherId}")
    public ResponseEntity<VoucherAdminResponse> updateStatusVoucherAdmin(@PathVariable Long voucherId,
                                                                         @RequestParam Status status,
                                                                         HttpServletRequest servletRequest) {
        String username = (String) servletRequest.getAttribute("username");
        if (voucherId == null) {
            throw new IllegalArgumentException("Mã giảm giá không được để trống!");
        }

        return ResponseEntity.ok(voucherAdminService.updateStatusVoucherAdmin(voucherId, status, username));
    }

}
