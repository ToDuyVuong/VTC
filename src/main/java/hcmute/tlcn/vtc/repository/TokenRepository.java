package hcmute.tlcn.vtc.repository;

import java.util.List;
import java.util.Optional;

import hcmute.tlcn.vtc.model.entity.vtc.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(value = """
      select t from Token t inner join Customer c\s
      on t.customer.customerId = c.customerId\s
      where c.customerId = :customerId and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByCustomer(Long customerId);


//    @Query("select t from Token t inner join Customer c " +
//            "on t.customer.customerId = c.customerId " +
//            "where c.customerId = :customerId and (t.expired = false or t.revoked = false)")
//    List<Token> findAllValidTokenByCustomer(@Param("customerId") Long customerId);

    Optional<Token> findByToken(String token);



}