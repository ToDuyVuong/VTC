package hcmute.tlcn.vtc.service.location.impl;

import hcmute.tlcn.vtc.model.data.location.ListDistrictResponse;
import hcmute.tlcn.vtc.model.dto.location_dto.DistrictDTO;
import hcmute.tlcn.vtc.model.entity.location.District;
import hcmute.tlcn.vtc.repository.location.DistrictRepository;
import hcmute.tlcn.vtc.service.location.IDistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements IDistrictService {
    private final DistrictRepository districtRepository;


    @Override
    public ListDistrictResponse getAllDistrictByProvinceCode(String provinceCode) {
        List<District> districts = districtRepository.findAllByProvince_ProvinceCode(provinceCode)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy quận huyện nào có mã tỉnh thành phố là " + provinceCode));


        List<DistrictDTO> districtDTOs = new ArrayList<>();
        if (!districts.isEmpty()) {
            districtDTOs = DistrictDTO.convertEntitiesToDTOs(districts);
        }
        ListDistrictResponse response = new ListDistrictResponse();
        response.setDistrictDTOs(districtDTOs);
        response.setCount(response.getDistrictDTOs().size());
        response.setProvinceCode(provinceCode);
        response.setCode(200);
        response.setMessage("Lấy danh sách quận huyện thành công.");
        response.setStatus("OK");
        return response;
    }

}
