package hcmute.tlcn.vtc.controller.guest;

import hcmute.tlcn.vtc.model.data.guest.ListVoucherResponse;
import hcmute.tlcn.vtc.model.data.guest.VoucherResponse;
import hcmute.tlcn.vtc.model.extra.VoucherType;
import hcmute.tlcn.vtc.service.guest.IVoucherService;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/voucher")
@RequiredArgsConstructor
public class VoucherController {

    @Autowired
    private IVoucherService voucherService;

    @GetMapping("/detail/{voucherId}")
    public ResponseEntity<VoucherResponse> getVoucherByVoucherId(@PathVariable Long voucherId) {
        if (voucherId == null) {
            throw new NotFoundException("Mã giảm giá không được để trống!");
        }
        return ResponseEntity.ok(voucherService.getVoucherByVoucherId(voucherId));
    }


    @GetMapping("/list-on-shop/{shopId}")
    public ResponseEntity<ListVoucherResponse> listVoucherByShopId(@PathVariable Long shopId) {
        if (shopId == null) {
            throw new NotFoundException("Mã cửa hàng không được để trống!");
        }
        return ResponseEntity.ok(voucherService.listVoucherByShopId(shopId));
    }

    @GetMapping("/list-on-system")
    public ResponseEntity<ListVoucherResponse> listVoucherSystem() {
        return ResponseEntity.ok(voucherService.listVoucherSystem());
    }

    @GetMapping("/list-by-type/{type}")
    public ResponseEntity<ListVoucherResponse> listVoucherByType(@PathVariable VoucherType type) {
        if (type == null) {
            throw new NotFoundException("Loại mã giảm giá không được để trống!");
        }
        return ResponseEntity.ok(voucherService.listVoucherByType(type));
    }

    @GetMapping("/list-all")
    public ResponseEntity<ListVoucherResponse> listAllVoucher() {
        return ResponseEntity.ok(voucherService.allVoucher());
    }










}
