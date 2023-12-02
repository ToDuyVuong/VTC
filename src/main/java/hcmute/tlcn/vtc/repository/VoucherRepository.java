package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.vtc.Shop;
import hcmute.tlcn.vtc.model.entity.vtc.Voucher;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.model.extra.VoucherType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {

    boolean existsByCodeAndShopShopId(String code, Long shopId);

    boolean existsByCodeAndShopNull(String code);

    Optional<Voucher> findByCode(String code);

    Optional<Voucher> findByCodeAndShopShopId(String code, Long shopId);

    Optional<List<Voucher>> findAllByStatus(Status status);

    Optional<Voucher> findByVoucherIdAndShopShopId(Long voucherId, Long shopId);

    Optional<Voucher> findByCodeAndShopShopIdAndStatusNot(String code, Long shopId, Status status);


    Optional<List<Voucher>> findAllByShopShopIdAndStatus(Long shopId, Status status);

    Optional<List<Voucher>> findAllByCustomerUsernameAndStatusNot(String username, Status status);

    Optional<List<Voucher>> findAllByShopAndStatusNot(Shop shop, Status status);

    Optional<List<Voucher>> findAllByShopNullAndStatusNot(Status status);


    Optional<List<Voucher>> findAllByShopAndStatus(Shop shop, Status status);

    Optional<List<Voucher>> findAllByShopNullAndStatus(Status status);


    Optional<List<Voucher>> findAllByShopAndStatusNotAndType(Shop shop, Status status, VoucherType type);

    Optional<List<Voucher>> findAllByShopShopIdAndStatusAndType(Long shopId, Status status, VoucherType type);

    Optional<List<Voucher>> findAllByShopNullAndStatusNotAndType(Status status, VoucherType type);

    Optional<List<Voucher>> findAllByStatusAndType(Status status, VoucherType type);

    @Query(value = "SELECT v.* FROM voucher v " +
            "JOIN customer_voucher cv ON v.voucher_id = cv.voucher_id " +
            "JOIN customer c ON cv.customer_id = c.customer_id " +
            "WHERE c.username = ?1 AND cv.used = ?2", nativeQuery = true)
    Optional<List<Voucher>> getAllByUsernameAndUsed(String username, boolean used);



}