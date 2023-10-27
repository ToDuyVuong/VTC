package hcmute.tlcn.vtc.controller.vendor;

import hcmute.tlcn.vtc.model.dto.vendor.request.VoucherShopRequest;
import hcmute.tlcn.vtc.model.dto.vendor.response.ListVoucherShopResponse;
import hcmute.tlcn.vtc.model.dto.vendor.response.VoucherShopResponse;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.model.extra.VoucherType;
import hcmute.tlcn.vtc.service.vendor.IVoucherShopService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor/shop/voucher")
@RequiredArgsConstructor
public class VoucherController {

    @Autowired
    private IVoucherShopService voucherShopService;

    @PostMapping("/add")
    public ResponseEntity<VoucherShopResponse> addNewVoucher(@RequestBody  VoucherShopRequest request,
                                                             HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");

        request.setUsername(username);
        request.validateCreate();



        return ResponseEntity.ok(voucherShopService.addNewVoucher(request, username));
    }


    @GetMapping("/detail/{voucherId}")
    public ResponseEntity<VoucherShopResponse> getVoucherByVoucherId(@PathVariable Long voucherId,
                                                                     HttpServletRequest httpServletRequest) {
        if (voucherId == null) {
            throw new IllegalArgumentException("Mã giảm giá không được để trống!");
        }
        String username = (String) httpServletRequest.getAttribute("username");

        return ResponseEntity.ok(voucherShopService.getVoucherByVoucherId(voucherId, username));
    }

    @GetMapping("/get-all-shop")
    public ResponseEntity<ListVoucherShopResponse> getAllVoucher(HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");

        return ResponseEntity.ok(voucherShopService.getListVoucherByUsername(username));
    }

    @GetMapping("/get-all-shop-status")
    public ResponseEntity<ListVoucherShopResponse> getAllVoucherByStatus(@RequestParam Status status,
                                                                         HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");

        return ResponseEntity.ok(voucherShopService.getListVoucherByUsernameAndStatus(username, status));
    }


    @GetMapping("/get-all-shop-type")
    public ResponseEntity<ListVoucherShopResponse> getAllVoucherByType(@RequestParam VoucherType type,
                                                                       HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");

        return ResponseEntity.ok(voucherShopService.getListVoucherByUsernameAndVoucherType(username, type));
    }

    @PutMapping("/update")
    public ResponseEntity<VoucherShopResponse> updateVoucher(@RequestBody  VoucherShopRequest request,
                                                             HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");

        request.setUsername(username);
        request.validateUpdate();

        return ResponseEntity.ok(voucherShopService.updateVoucher(request, username));
    }


    @PatchMapping("/update-status/{voucherId}")
    public ResponseEntity<VoucherShopResponse> updateStatusVoucher(@PathVariable Long voucherId,
                                                                   @RequestParam Status status,
                                                                   HttpServletRequest httpServletRequest) {
        if (voucherId == null) {
            throw new IllegalArgumentException("Mã giảm giá không được để trống!");
        }

        String username = (String) httpServletRequest.getAttribute("username");

        return ResponseEntity.ok(voucherShopService.updateStatusVoucher(voucherId, status, username));
    }
}
