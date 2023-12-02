package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.vtc.Messenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessengerRepository  extends JpaRepository<Messenger, Long> {

    Optional<List<Messenger>> findAllByRomChatRomChatId(Long id);


}
