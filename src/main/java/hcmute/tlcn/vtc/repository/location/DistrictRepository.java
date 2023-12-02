package hcmute.tlcn.vtc.repository.location;


import hcmute.tlcn.vtc.model.entity.location.District;
import hcmute.tlcn.vtc.model.entity.location.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District, String> {
    // Additional custom queries can be added here if needed
    Optional<List<District>> findAllByProvince_ProvinceCode(String provinceCode);

    Optional<List<District>> findAllByAdministrativeUnit_AdministrativeUnitId(Integer administrativeUnitId);

    Optional<District> findByDistrictCode(String districtCode);

}