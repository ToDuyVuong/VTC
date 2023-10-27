package hcmute.tlcn.vtc.service.user.impl;

import hcmute.tlcn.vtc.model.data.dto.VoucherDTO;
import hcmute.tlcn.vtc.model.data.user.response.ListVoucherResponse;
import hcmute.tlcn.vtc.model.data.user.response.VoucherResponse;
import hcmute.tlcn.vtc.model.entity.Voucher;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.model.extra.VoucherType;
import hcmute.tlcn.vtc.repository.VoucherRepository;
import hcmute.tlcn.vtc.service.user.ICustomerService;
import hcmute.tlcn.vtc.service.user.IVoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoucherServiceImpl implements IVoucherService {

    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private ICustomerService customerService;



    @Override
    public VoucherResponse getVoucherByVoucherId(Long voucherId) {
        return voucherResponse(getVoucherById(voucherId));
    }


    @Override
    public ListVoucherResponse listVoucherByShopId(Long shopId) {
        List<Voucher> vouchers = voucherRepository.findAllByShopShopIdAndStatus(shopId, Status.ACTIVE)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy mã giảm giá trên cửa hàng!"));
        return listVoucherResponse(vouchers, "Lấy danh sách mã giảm giá trong cửa hàng thành công.");
    }


    @Override
    public ListVoucherResponse listVoucherByType(VoucherType type) {
        List<Voucher> vouchers = voucherRepository.findAllByStatusAndType(Status.ACTIVE, type)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy mã giảm giá!"));

        return listVoucherResponse(vouchers, "Lấy danh sách mã giảm giá theo loại thành công.");
    }


    @Override
    public ListVoucherResponse listVoucherSystem() {
        List<Voucher> vouchers = voucherRepository.findAllByShopNullAndStatus(Status.ACTIVE)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy mã giảm giá!"));

        return listVoucherResponse(vouchers, "Lấy danh sách mã giảm giá cửa hệ thống thành công.");
    }


    @Override
    public ListVoucherResponse allVoucher() {
        List<Voucher> vouchers = voucherRepository.findAllByStatus(Status.ACTIVE)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy mã giảm giá!"));

        return listVoucherResponse(vouchers, "Lấy danh sách tất cả khã dụng mã giảm giá thành công.");
    }







    private VoucherResponse voucherResponse(Voucher voucher) {
        VoucherResponse response = new VoucherResponse();
        response.setVoucherDTO(VoucherDTO.convertEntityToDTO(voucher));
        response.setMessage("Lấy mã giảm giá thành công.");
        response.setStatus("ok");
        return response;
    }

    private ListVoucherResponse listVoucherResponse(List<Voucher> vouchers, String message) {
        ListVoucherResponse response = new ListVoucherResponse();
        response.setVoucherDTOs(VoucherDTO.convertToListDTO(vouchers));
        response.setMessage(message);
        response.setStatus("ok");
        response.setCount(vouchers.size());
        response.setCode(200);



        return response;
    }


    private Voucher getVoucherById(Long voucherId) {
        Voucher voucher = voucherRepository.findById(voucherId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy mã giảm giá!"));
        if (voucher.getStatus().equals(Status.DELETED)) {
            throw new IllegalArgumentException("Mã giảm giá đã bị xóa!");
        }
        return voucher;

    }


}
