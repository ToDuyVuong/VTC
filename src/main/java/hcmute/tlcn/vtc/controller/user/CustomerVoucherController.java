package hcmute.tlcn.vtc.controller.user;

import hcmute.tlcn.vtc.model.data.user.response.CustomerVoucherResponse;
import hcmute.tlcn.vtc.model.data.user.response.ListCustomerVoucherResponse;
import hcmute.tlcn.vtc.service.user.ICustomerVoucherService;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer/voucher")
@RequiredArgsConstructor
public class CustomerVoucherController {

    @Autowired
    private ICustomerVoucherService customerVoucherService;

    @PostMapping("/save")
    public ResponseEntity<CustomerVoucherResponse> saveVoucher(@RequestParam("voucherId") Long voucherId,
                                                               HttpServletRequest request) {
        if (voucherId == null) {
            throw new NotFoundException("Mã giảm giá không được để trống.");
        }
        String username = (String) request.getAttribute("username");
        return ResponseEntity.ok(customerVoucherService.saveVoucher(voucherId, username));
    }

    @GetMapping("/list")
    public ResponseEntity<ListCustomerVoucherResponse> listCustomerVoucher(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        return ResponseEntity.ok(customerVoucherService.listCustomerVoucherByUsername(username));
    }


    @DeleteMapping("/delete/{voucherId}")
    public ResponseEntity<CustomerVoucherResponse> deleteCustomerVoucher(@PathVariable("voucherId") Long voucherId,
                                                                         HttpServletRequest request) {
        if (voucherId == null) {
            throw new NotFoundException("Mã giảm giá không được để trống.");
        }
        String username = (String) request.getAttribute("username");
        return ResponseEntity.ok(customerVoucherService.deleteCustomerVoucher(voucherId, username));
    }


}
