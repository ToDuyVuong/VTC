package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.entity.Address;
import hcmute.tlcn.vtc.entity.Customer;
import hcmute.tlcn.vtc.entity.extra.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<List<Address>> findAllByCustomerAndStatusNot(Customer customer, Status status);
}