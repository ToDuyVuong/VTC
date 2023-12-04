package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.vtc.Customer;
import hcmute.tlcn.vtc.model.extra.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

//    Customer findByUsername(String username);
//    Customer findByEmail(String email);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByUsername(String username);

    Optional<Customer> findByUsernameAndStatus(String username, Status status);


    int countAllByStatus(Status status);

    Optional<Page<Customer>> findAllByStatus(Status status, Pageable pageable);

    Optional<Page<Customer>> findAllByStatusOrderByFullName(Status status, Pageable pageable);

    Optional<Page<Customer>> findAllByStatusOrderByFullNameDesc(Status status, Pageable pageable);

    Optional<Page<Customer>> findAllByStatusOrderByCreateAtAsc(Status status, Pageable pageable);

    Optional<Page<Customer>> findAllByStatusOrderByCreateAtDesc(Status status, Pageable pageable);


    int countAllByFullNameContainingAndStatus(String fullName, Status status);

    Optional<Page<Customer>> findAllByFullNameContainingAndStatus(String fullName, Status status, Pageable pageable);

}
