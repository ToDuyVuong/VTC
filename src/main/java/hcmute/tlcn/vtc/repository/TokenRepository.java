package hcmute.tlcn.vtc.repository;

import java.util.List;
import java.util.Optional;

import hcmute.tlcn.vtc.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
      select t from Token t inner join Customer c\s
      on t.customer.customerId = c.customerId\s
      where c.customerId = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByCustomer(Integer id);

    Optional<Token> findByToken(String token);
}