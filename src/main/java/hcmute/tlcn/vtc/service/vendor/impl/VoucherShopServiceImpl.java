package hcmute.tlcn.vtc.service.vendor.impl;

import hcmute.tlcn.vtc.model.data.dto.VoucherDTO;
import hcmute.tlcn.vtc.model.data.vendor.request.VoucherShopRequest;
import hcmute.tlcn.vtc.model.data.vendor.response.ListVoucherShopResponse;
import hcmute.tlcn.vtc.model.data.vendor.response.VoucherShopResponse;
import hcmute.tlcn.vtc.model.entity.Shop;
import hcmute.tlcn.vtc.model.entity.Voucher;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.model.extra.VoucherType;
import hcmute.tlcn.vtc.repository.VoucherRepository;
import hcmute.tlcn.vtc.service.vendor.IVoucherShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VoucherShopServiceImpl implements IVoucherShopService {


    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private ShopServiceImpl shopService;


    @Override
    @Transactional
    public VoucherShopResponse addNewVoucher(VoucherShopRequest request, String username) {
        Shop shop = checkVoucherOnShop(request.getCode(), username);
        Voucher voucher = VoucherShopRequest.convertCreateToVoucher(request);
        voucher.setShop(shop);

        try {
            voucherRepository.save(voucher);

            return voucherShopResponse(voucher, "Thêm mới mã giảm giá thành công.", "success");
        } catch (Exception e) {
            throw new IllegalArgumentException("Thêm mới mã giảm giá thất bại!");
        }

    }


    @Override
    public VoucherShopResponse getVoucherByVoucherId(Long voucherId, String username) {
        Voucher voucher = getVoucherByVoucherIdAndUsername(voucherId, username);

        return voucherShopResponse(voucher, "Lấy mã giảm giá thành công.", "ok");
    }



    @Override
    public ListVoucherShopResponse getListVoucherByUsername(String username) {
        Shop shop = shopService.getShopByUsername(username);

        List<Voucher> vouchers = voucherRepository.findAllByShopAndStatusNot(shop, Status.DELETED)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy mã giảm giá!"));

        return listVoucherShopResponse(vouchers, "Lấy danh sách mã giảm giá thành công.", shop.getShopId(), shop.getName());
    }


    @Override
    public ListVoucherShopResponse getListVoucherByUsernameAndStatus(String username, Status status) {
        Shop shop = shopService.getShopByUsername(username);

        List<Voucher> vouchers = voucherRepository.findAllByShopAndStatus(shop, status)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy mã giảm giá!"));

        return listVoucherShopResponse(vouchers, "Lấy danh sách mã giảm giá theo trạng thái thành công.", shop.getShopId(), shop.getName());
    }


    @Override
    public ListVoucherShopResponse getListVoucherByUsernameAndVoucherType(String username, VoucherType type) {
        Shop shop = shopService.getShopByUsername(username);

        List<Voucher> vouchers = voucherRepository.findAllByShopAndStatusNotAndType(shop, Status.DELETED, type)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy mã giảm giá!"));

        return listVoucherShopResponse(vouchers, "Lấy danh sách mã giảm giá theo loại thành công.", shop.getShopId(), shop.getName());
    }


    @Override
    @Transactional
    public VoucherShopResponse updateVoucher(VoucherShopRequest request, String username) {
        Voucher voucher = getVoucherByCodeAndUsername(request.getCode(), username);
        if (!voucher.getCode().equals(request.getCode()) && existVoucherCodeOnShop(request.getCode(), voucher.getShop().getShopId())) {
            throw new IllegalArgumentException("Mã giảm giá đã tồn tại trên cửa hàng này!");
        }
        VoucherShopRequest.convertUpdateToVoucher(request, voucher);

        try {
            voucherRepository.save(voucher);

            return voucherShopResponse(voucher, "Cập nhật mã giảm giá thành công.", "success");
        } catch (Exception e) {
            throw new IllegalArgumentException("Cập nhật mã giảm giá thất bại!");
        }
    }

    @Override
    @Transactional
    public VoucherShopResponse updateStatusVoucher(Long voucherId, Status status, String username) {
        Voucher voucher = getVoucherByVoucherIdAndUsername(voucherId, username);

        if(status != Status.ACTIVE && status != Status.INACTIVE && status != Status.DELETED && status != Status.CANCEL){
                throw new IllegalArgumentException("Trạng thái không hợp lệ!");
        }

        if (voucher.getStatus() == Status.DELETED) {
            throw new IllegalArgumentException("Mã giảm giá đã bị xóa!");
        }
        voucher.setStatus(status);

        try {
            voucherRepository.save(voucher);

            return voucherShopResponse(voucher, "Cập nhật trạng thái mã giảm giá thành công.", "success");
        } catch (Exception e) {
            throw new IllegalArgumentException("Cập nhật trạng thái mã giảm giá thất bại!");
        }
    }



    private boolean existVoucherCodeOnShop(String code, Long shopId) {
        return voucherRepository.existsByCodeAndShopShopId(code, shopId);
    }

    private Shop checkVoucherOnShop(String code, String username) {
        Shop shop = shopService.getShopByUsername(username);
        if (voucherRepository.existsByCodeAndShopShopId(code, shop.getShopId())) {
            throw new IllegalArgumentException("Mã giảm giá đã tồn tại trên cửa hàng này!");
        }

        return shop;
    }


    private Voucher getVoucherByCodeAndUsername(String code, String username) {
        Shop shop = shopService.getShopByUsername(username);
        Voucher voucher = voucherRepository.findByCodeAndShopShopId(code, shop.getShopId())
                .orElseThrow(() -> new IllegalArgumentException("Mã giảm giá không tồn tại!"));
        if (voucher == null) {
            throw new IllegalArgumentException("Mã giảm giá không tồn tại!");
        }
        if (voucher.getStatus() == Status.DELETED) {
            throw new IllegalArgumentException("Mã giảm giá đã bị xóa!");
        }

        return voucher;
    }


    private Voucher getVoucherByVoucherIdAndUsername(Long voucherId, String username) {
        Shop shop = shopService.getShopByUsername(username);
        Voucher voucher = voucherRepository.findByVoucherIdAndShopShopId(voucherId, shop.getShopId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy mã giảm giá!"));
        if (voucher == null) {
            throw new IllegalArgumentException("Mã giảm giá không tồn tại!");
        }

        return voucher;
    }

    private VoucherShopResponse voucherShopResponse(Voucher voucher, String message, String status) {
        VoucherShopResponse response = new VoucherShopResponse();
        response.setVoucherDTO(VoucherDTO.convertEntityToDTO(voucher));
        response.setCode(200);
        response.setMessage(message);
        response.setStatus(status);
        response.setShopName(voucher.getShop().getName());
        response.setShopId(voucher.getShop().getShopId());

        return response;
    }

    private ListVoucherShopResponse listVoucherShopResponse(List<Voucher> vouchers, String message, Long shopId, String shopName) {
        ListVoucherShopResponse response = new ListVoucherShopResponse();
        response.setVoucherDTOs(VoucherDTO.convertToListDTO(vouchers));
        response.setCode(200);
        response.setMessage(message);
        response.setStatus("ok");
        response.setCount(vouchers.size());
        response.setShopId(shopId);
        response.setShopName(shopName);

        return response;
    }

}
