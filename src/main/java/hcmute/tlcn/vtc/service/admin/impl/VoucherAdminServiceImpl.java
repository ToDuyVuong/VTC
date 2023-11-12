package hcmute.tlcn.vtc.service.admin.impl;

import hcmute.tlcn.vtc.model.data.admin.response.ListVoucherAdminResponse;
import hcmute.tlcn.vtc.model.dto.VoucherDTO;
import hcmute.tlcn.vtc.model.data.admin.request.VoucherAdminRequest;
import hcmute.tlcn.vtc.model.data.admin.response.VoucherAdminResponse;
import hcmute.tlcn.vtc.model.entity.Customer;
import hcmute.tlcn.vtc.model.entity.Voucher;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.model.extra.VoucherType;
import hcmute.tlcn.vtc.repository.VoucherRepository;
import hcmute.tlcn.vtc.service.admin.IVoucherAdminService;
import hcmute.tlcn.vtc.service.user.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoucherAdminServiceImpl implements IVoucherAdminService {

    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private ICustomerService customerService;


    @Override
    public VoucherAdminResponse addNewVoucherAdmin(VoucherAdminRequest request) {
        Customer customer = customerService.getCustomerByUsername(request.getUsername());
        if (voucherRepository.existsByCodeAndShopNull(request.getCode())) {
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


    @Override
    public VoucherAdminResponse getVoucherAdminByVoucherId(Long voucherId) {
        return voucherAdminResponse(getVoucherByVoucherId(voucherId), "Lấy mã giảm giá thành công.", "ok");
    }


    @Override
    public ListVoucherAdminResponse getListVoucherAdmin(String username) {
        List<Voucher> vouchers = voucherRepository.findAllByShopNullAndStatusNot(Status.DELETED)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy mã giảm giá!"));

        return listVoucherAdminResponse(vouchers, "Lấy danh sách mã giảm giá thành công.", username);
    }


    @Override
    public ListVoucherAdminResponse getListVoucherAdminByUsername(String username) {
        List<Voucher> vouchers = voucherRepository.findAllByCustomerUsernameAndStatusNot(username, Status.DELETED)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy mã giảm giá!"));

        return listVoucherAdminResponse(vouchers, "Lấy danh sách mã giảm giá thành công.", username);
    }


    @Override
    public ListVoucherAdminResponse getListVoucherAdminByStatus(String username, Status status) {
        List<Voucher> vouchers = voucherRepository.findAllByShopNullAndStatus(status)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy mã giảm giá!"));

        return listVoucherAdminResponse(vouchers, "Lấy danh sách mã giảm giá thành công.", username);
    }


    @Override
    public ListVoucherAdminResponse getListVoucherAdminByType(String username, VoucherType type) {
        List<Voucher> vouchers = voucherRepository.findAllByShopNullAndStatusNotAndType(Status.DELETED, type)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy mã giảm giá!"));

        return listVoucherAdminResponse(vouchers, "Lấy danh sách mã giảm giá thành công.", username);
    }


    @Override
    @Transactional
    public VoucherAdminResponse updateVoucherAdmin(VoucherAdminRequest request, String username) {
        Voucher voucher = getVoucherUpdate(request.getVoucherId(), username);
        if (!voucher.getCode().equals(request.getCode()) && checkVoucherCode(request.getCode())) {
            throw new IllegalArgumentException("Mã giảm giá đã tồn tại trong hệ thống.");
        }
        if (voucher.getStatus() == Status.DELETED) {
            throw new IllegalArgumentException("Mã giảm giá đã bị xóa!");
        }

        VoucherAdminRequest.convertUpdateToVoucher(request, voucher);

        try {
            voucherRepository.save(voucher);

            return voucherAdminResponse(voucher, "Cập nhật mã giảm giá thành công.", "success");
        } catch (Exception e) {
            throw new IllegalArgumentException("Cập nhật mã giảm giá thất bại!");
        }
    }


    @Override
    @Transactional
    public VoucherAdminResponse updateStatusVoucherAdmin(Long voucherId, Status status, String username) {
        Voucher voucher = getVoucherUpdate(voucherId, username);
        if(status != Status.ACTIVE && status != Status.INACTIVE && status != Status.DELETED && status != Status.CANCEL){
            throw new IllegalArgumentException("Trạng thái không hợp lệ!");
        }

        if (voucher.getStatus() == Status.DELETED) {
            throw new IllegalArgumentException("Mã giảm giá đã bị xóa!");
        }
        voucher.setStatus(status);

        try {
            voucherRepository.save(voucher);

            return voucherAdminResponse(voucher, "Cập nhật trạng thái mã giảm giá thành công.", "success")  ;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cập nhật trạng thái mã giảm giá thất bại!");
        }
    }


    @Override
    public Voucher checkVoucherSystem(Long voucherId) {
        Voucher voucher = getVoucherByVoucherId(voucherId);

//        if(voucher.getStartDate().before(new Date())){
//            throw new IllegalArgumentException("Mã giảm giá chưa có hiệu lực!");
//        }
//        if (voucher.getEndDate().after(new Date())) {
//            throw new IllegalArgumentException("Mã giảm giá đã hết hạn!");
//        }

        Instant now = Instant.now(); // Sử dụng Instant thay vì Date
        Instant voucherStartDate = voucher.getStartDate().toInstant(); // Chuyển startDate của voucher thành Instant
        Instant voucherEndDate = voucher.getEndDate().toInstant();


        if (voucherStartDate.isAfter(now)) { // So sánh theo Instant
            System.out.println("Voucher start date: " + voucher.getStartDate());
            System.out.println("start date: " + Date.from(now)); // Chuyển Instant về Date nếu cần
            throw new IllegalArgumentException("Mã giảm giá chưa có hiệu lực!");
        }


        if (voucherEndDate.isBefore(now)) {
            throw new IllegalArgumentException("Mã giảm giá đã hết hạn!");
        }

        if (voucher.getStatus() == Status.DELETED) {
            throw new IllegalArgumentException("Mã giảm giá đã bị xóa!");
        }

        if (voucher.getQuantityUsed().equals(voucher.getQuantity())) {
            throw new IllegalArgumentException("Mã giảm giá đã hết lượt sử dụng!");
        }

        return voucher;
    }




    private Voucher getVoucherUpdate(Long voucherId,  String username) {
        Voucher voucher = getVoucherByVoucherId(voucherId);
        if (!voucher.getCustomer().getUsername().equals(username)) {
            throw new IllegalArgumentException("Bạn không có quyền cập nhật mã giảm giá này!");
        }
        return voucher;
    }


    private boolean checkVoucherCode(String code) {
        return voucherRepository.existsByCodeAndShopNull(code);
    }

    private VoucherAdminResponse voucherAdminResponse(Voucher voucher, String message, String status) {
        VoucherAdminResponse response = new VoucherAdminResponse();
        response.setVoucherDTO(VoucherDTO.convertEntityToDTO(voucher));
        response.setCode(200);
        response.setMessage(message);
        response.setStatus(status);
        response.setUsername(voucher.getCustomer().getUsername());

        return response;
    }

    private ListVoucherAdminResponse listVoucherAdminResponse(List<Voucher> vouchers, String message, String username) {
        ListVoucherAdminResponse response = new ListVoucherAdminResponse();
        response.setVoucherDTOs(VoucherDTO.convertToListDTO(vouchers));
        response.setCode(200);
        response.setMessage(message);
        response.setStatus("ok");
        response.setCount(vouchers.size());
        response.setUsername(username);

        return response;
    }

    private Voucher getVoucherByVoucherId(Long voucherId) {
        Voucher voucher = voucherRepository.findById(voucherId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy mã giảm giá!"));
        if (voucher == null) {
            throw new IllegalArgumentException("Mã giảm giá không tồn tại!");
        }
        if (voucher.getCustomer() == null) {
            throw new IllegalArgumentException("Mã giảm giá không thuộc quyền quản lý của hệ thống!");
        }

        return voucher;
    }


}
