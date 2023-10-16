package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

//    Customer findByUsername(String username);
//    Customer findByEmail(String email);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByUsername(String username);






}
