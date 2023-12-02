package hcmute.tlcn.vtc.service.user.impl;

import hcmute.tlcn.vtc.model.dto.VoucherDTO;
import hcmute.tlcn.vtc.model.data.user.response.CustomerVoucherResponse;
import hcmute.tlcn.vtc.model.data.user.response.ListCustomerVoucherResponse;
import hcmute.tlcn.vtc.model.entity.vtc.CustomerVoucher;
import hcmute.tlcn.vtc.model.entity.vtc.Voucher;
import hcmute.tlcn.vtc.repository.CustomerVoucherRepository;
import hcmute.tlcn.vtc.repository.VoucherRepository;
import hcmute.tlcn.vtc.service.guest.IVoucherService;
import hcmute.tlcn.vtc.service.user.ICustomerService;
import hcmute.tlcn.vtc.service.user.ICustomerVoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerVoucherServiceImpl implements ICustomerVoucherService {

    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private CustomerVoucherRepository customerVoucherRepository;
    @Autowired
    private IVoucherService voucherService;
    @Autowired
    private ICustomerService customerService;


    @Override
    public CustomerVoucherResponse saveVoucher(Long voucherId, String username) {
        if (customerVoucherRepository.existsByCustomerUsernameAndVoucherVoucherId(username, voucherId)) {
            throw new IllegalArgumentException("Bạn đã lưu mã giảm giá này rồi!");
        }

        CustomerVoucher customerVoucher = new CustomerVoucher();
        customerVoucher.setCustomer(customerService.getCustomerByUsername(username));
        customerVoucher.setVoucher(voucherService.getVoucherById(voucherId));
        customerVoucher.setUsed(false);

        try {
            customerVoucherRepository.save(customerVoucher);
            return customerVoucherResponse(customerVoucher.getVoucher(),"Thêm mã giảm giá thành công!", username);
        } catch (Exception e) {
            throw new IllegalArgumentException("Lưu mã giảm giá thất bại!");
        }

    }


    @Override
    public ListCustomerVoucherResponse listCustomerVoucherByUsername(String username) {
        List<Voucher> vouchers = voucherRepository.getAllByUsernameAndUsed(username, false)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy mã giảm giá!"));

        return listCustomerVoucherResponse(vouchers, "Lấy danh sách mã giảm giá thành công.", username);
    }


    @Override
    public CustomerVoucherResponse deleteCustomerVoucher(Long voucherId, String username) {
        if (!customerVoucherRepository.existsByCustomerUsernameAndVoucherVoucherId(username, voucherId)) {
            throw new IllegalArgumentException("Bạn chưa lưu mã giảm giá này!");
        }

        CustomerVoucher customerVoucher = customerVoucherRepository.findByCustomerUsernameAndVoucherVoucherId(username, voucherId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy mã giảm giá!"));

        if (customerVoucher.isUsed()) {
            throw new IllegalArgumentException("Mã giảm giá đã được sử dụng!");
        }

        try {
            customerVoucherRepository.delete(customerVoucher);
            return customerVoucherResponse(customerVoucher.getVoucher(),"Xóa mã giảm giá thành công!", username);
        } catch (Exception e) {
            throw new IllegalArgumentException("Xóa mã giảm giá thất bại!");
        }
    }



    private CustomerVoucherResponse customerVoucherResponse(Voucher voucher, String message, String username) {
        CustomerVoucherResponse response = new CustomerVoucherResponse();
        response.setVoucherDTO(VoucherDTO.convertEntityToDTO(voucher));
        response.setUsername(username);
        response.setMessage(message);
        response.setStatus("ok");
        return response;
    }


    private ListCustomerVoucherResponse listCustomerVoucherResponse(List<Voucher> vouchers, String message, String username) {
        ListCustomerVoucherResponse response = new ListCustomerVoucherResponse();
        response.setVoucherDTOs(VoucherDTO.convertToListDTO(vouchers));
        response.setUsername(username);
        response.setMessage(message);
        response.setStatus("ok");
        response.setCount(vouchers.size());
        response.setCode(200);

        return response;
    }

}
