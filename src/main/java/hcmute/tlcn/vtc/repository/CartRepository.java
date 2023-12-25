package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.vtc.Cart;
import hcmute.tlcn.vtc.model.extra.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    boolean existsByCartIdAndCustomerUsername(Long cartId, String username);

    boolean existsByProductVariantProductVariantIdAndCustomerUsernameAndStatus(Long productVariantId, String username, Status status);

    Optional<Cart> findByProductVariantProductVariantIdAndCustomerUsernameAndStatus(Long productVariantId, String username, Status status);

    Optional<Cart> findByCustomerUsernameAndCartId(String username, Long cartId);

    Optional<List<Cart>> findAllByCustomerUsername(String username);

    Optional<Cart> findByCartIdAndCustomerUsername(Long cartId, String username);

    Optional<List<Cart>> findAllByCustomerUsernameAndStatus(String username, Status status);

    Optional<List<Cart>> findAllByCustomerUsernameAndStatusAndCartIdIn(String username, Status status, List<Long> cartIds);

    Optional<List<Cart>> findAllByCustomerUsernameAndProductVariantProductCategoryShopShopIdAndStatus(String username, Long shopId, Status status);
}
