package hcmute.tlcn.vtc.repository.manager;

import hcmute.tlcn.vtc.model.entity.vtc.manager.Manager;
import hcmute.tlcn.vtc.model.extra.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    boolean existsByManagerUsername(String username);

    boolean existsByManagerUsernameAndStatus(String username, Status status);

    int countAllBy();
    Optional<Page<Manager>> findAllByOrderByManagerFullName(Pageable pageable);
    int countAllByStatus(Status status);
    Optional<Page<Manager>> findAllByStatusOrderByManagerFullName(Status status, Pageable pageable);
    Optional<Manager> findByManagerUsername(String username);

    int countAllByManagerUsernameContainsAndStatus(String username, Status status);
    Optional<Page<Manager>> findAllByManagerUsernameContainingAndStatusOrderByManagerFullName(String username, Status status, Pageable pageable);
}



