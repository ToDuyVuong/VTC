package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByUsername(String username);
    Customer findByEmail(String email);
}
