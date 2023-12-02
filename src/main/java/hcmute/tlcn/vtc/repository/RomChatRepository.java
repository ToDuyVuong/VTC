package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.vtc.RomChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RomChatRepository extends JpaRepository<RomChat, Long> {

    @Query("SELECT f FROM RomChat f " +
            "where (f.receiver =:userName1 and f.sender =:userName2) " +
            "or (f.receiver =:userName2 and f.sender =:userName1)")
    public RomChat findChatRom(@Param("userName1") String userName1, @Param("userName2") String userName2);


    Optional<RomChat> findBySenderAndReceiver(String sender, String receiver);

    boolean existsBySenderAndReceiver(String sender, String receiver);

    Optional<RomChat> findByReceiverAndSender(String receiver, String sender);

    boolean existsByReceiverAndSender(String receiver, String sender);



}