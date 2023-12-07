package hcmute.tlcn.vtc.repository.manager;

import hcmute.tlcn.vtc.model.entity.vtc.manager.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository  extends JpaRepository<Manager, Long> {

boolean existsByManagerUsername(String username);



}



