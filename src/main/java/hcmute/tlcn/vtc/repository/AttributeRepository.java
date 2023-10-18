package hcmute.tlcn.vtc.repository;

import hcmute.tlcn.vtc.model.entity.Attribute;
import hcmute.tlcn.vtc.model.extra.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {


    Optional<Attribute> findByNameAndValueAndShop_ShopId(String name, String value, Long shopId);

    boolean existsByNameAndValueAndShop_ShopId(String name, String value, Long shopId);

//    Optional<Attribute> findByProductAttributeIdAndShop_ShopId(Long productAttributeId, Long shopId);

    List<Attribute> findAllByShop_ShopId(Long shopId);

    List<Attribute> findAllByShop_ShopIdAndActive(Long shopId, boolean active);

    List<Attribute> findAllByShop_ShopIdAndName(Long shopId, String name);



}
