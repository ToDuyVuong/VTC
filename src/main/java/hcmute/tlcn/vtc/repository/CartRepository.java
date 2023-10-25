package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    boolean existsByCartIdAndCustomerUsername(Long cartId, String username);

    boolean existsByProductVariantProductVariantIdAndCustomerUsername(Long productVariantId, String username);

    Optional<Cart> findByProductVariantProductVariantIdAndCustomerUsername(Long productVariantId, String username);
    Optional<List<Cart>> findAllByCustomerUsername(String username);

    Optional<Cart> findByCartIdAndCustomerUsername(Long cartId, String username);
}
