package hcmute.tlcn.vtc.repository.location;

import hcmute.tlcn.vtc.model.entity.location.AdministrativeUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdministrativeUnitRepository extends JpaRepository<AdministrativeUnit, Integer> {
    // Additional custom queries can be added here if needed

    Optional<AdministrativeUnit> findByAdministrativeUnitId(Integer administrativeUnitId);

}
