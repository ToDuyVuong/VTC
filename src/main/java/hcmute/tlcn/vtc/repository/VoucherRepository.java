package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.Shop;
import hcmute.tlcn.vtc.model.entity.Voucher;
import hcmute.tlcn.vtc.model.extra.Role;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.model.extra.VoucherType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {

    boolean existsByCodeAndShopShopId(String code, Long shopId);

    boolean existsByCodeAndShopNull(String code);

    Optional<Voucher> findByCode(String code);

    Optional<Voucher> findByCodeAndShopShopId(String code, Long shopId);
    Optional<Voucher> findByVoucherIdAndShopShopId(Long voucherId, Long shopId);

    Optional<Voucher> findByCodeAndShopShopIdAndStatusNot(String code, Long shopId, Status status);


    Optional<List<Voucher>> findAllByShopShopIdAndStatus(Long shopId, Status status);

    Optional<List<Voucher>>  findAllByCustomerUsernameAndStatusNot(String customer_username, Status status);

    Optional<List<Voucher>> findAllByShopAndStatusNot(Shop shop, Status status);
    Optional<List<Voucher>> findAllByShopNullAndStatusNot(Status status);


    Optional<List<Voucher>> findAllByShopAndStatus(Shop shop, Status status);
    Optional<List<Voucher>> findAllByShopNullAndStatus(Status status);


    Optional<List<Voucher>> findAllByShopAndStatusNotAndType(Shop shop, Status status, VoucherType type);

    Optional<List<Voucher>> findAllByShopNullAndStatusNotAndType(Status status, VoucherType type);

}