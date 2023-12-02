package hcmute.tlcn.vtc.repository.location;

import hcmute.tlcn.vtc.model.entity.location.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WardRepository extends JpaRepository<Ward, String> {
    // Additional custom queries can be added here if needed

    Optional<List<Ward>> findAllByDistrict_DistrictCode(String districtCode);

    Optional<List<Ward>> findAllByAdministrativeUnit_AdministrativeUnitId(Integer administrativeUnitId);

    Optional<Ward> findByWardCode(String wardCode);

}
