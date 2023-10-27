package hcmute.tlcn.vtc.service.admin.impl;

import hcmute.tlcn.vtc.model.data.dto.VoucherDTO;
import hcmute.tlcn.vtc.model.data.admin.request.VoucherAdminRequest;
import hcmute.tlcn.vtc.model.data.admin.response.VoucherAdminResponse;
import hcmute.tlcn.vtc.model.entity.Customer;
import hcmute.tlcn.vtc.model.entity.Voucher;
import hcmute.tlcn.vtc.repository.VoucherRepository;
import hcmute.tlcn.vtc.service.admin.IAdminService;
import hcmute.tlcn.vtc.service.admin.IVoucherAdminService;
import hcmute.tlcn.vtc.service.user.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoucherAdminServiceImpl implements IVoucherAdminService {

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private IAdminService adminService;

    @Autowired
    private ICustomerService customerService;


    @Override
    public VoucherAdminResponse addNewVoucherAdmin(VoucherAdminRequest request) {
        Customer customer = customerService.getCustomerByUsername(request.getUsername());
        if(voucherRepository.existsByCodeAndShopNull(request.getCode())) {
            throw new IllegalArgumentException("Mã giảm giá đã tồn tại trong hệ thống.");
        }
        Voucher voucher = VoucherAdminRequest.convertCreateToVoucher(request);
        voucher.setCustomer(customer);
        try {
            voucherRepository.save(voucher);

            return voucherAdminResponse(voucher, "Thêm mới mã giảm giá thành công.", "success");
        } catch (Exception e) {
            throw new IllegalArgumentException("Thêm mới mã giảm giá thất bại!");
        }
    }





    private VoucherAdminResponse voucherAdminResponse(Voucher voucher, String message, String status) {
        VoucherAdminResponse response = new VoucherAdminResponse();
        response.setVoucherDTO(VoucherDTO.convertEntityToDTO(voucher));
        response.setCode(200);
        response.setMessage(message);
        response.setStatus(status);
        response.setAdminName(voucher.getCustomer().getFullName());

        return response;
    }


}
