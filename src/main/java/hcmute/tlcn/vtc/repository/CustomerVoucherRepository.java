package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.vtc.CustomerVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerVoucherRepository extends JpaRepository<CustomerVoucher, Long> {

    Optional<List<CustomerVoucher>> findAllByCustomerUsername(String username);

    Optional<CustomerVoucher> findByCustomerUsernameAndVoucherVoucherId(String username, Long voucherId);

    boolean existsByCustomerUsernameAndVoucherVoucherId(String username, Long voucherId);
}