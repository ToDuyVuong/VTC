package hcmute.tlcn.vtc.repository.location;

import hcmute.tlcn.vtc.model.entity.location.AdministrativeRegion;
import hcmute.tlcn.vtc.model.entity.location.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, String> {
    // Additional custom queries can be added here if needed
    Optional<List<Province>> findAllByAdministrativeRegion_AdministrativeRegionId(Integer administrativeRegionId);

    Optional<List<Province>> findAllByAdministrativeUnit_AdministrativeUnitId(Integer administrativeUnitId);

    Optional<Province> findByProvinceCode(String provinceCode);




}
