package hcmute.tlcn.vtc.service.location.impl;

import hcmute.tlcn.vtc.model.data.location.ListWardResponse;
import hcmute.tlcn.vtc.model.dto.location_dto.WardDTO;
import hcmute.tlcn.vtc.model.entity.location.Ward;
import hcmute.tlcn.vtc.repository.location.WardRepository;
import hcmute.tlcn.vtc.service.location.IWardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WardServiceImpl implements IWardService {
    private final WardRepository wardRepository;


    @Override
    public ListWardResponse getAllWardByDistrictCode(String districtCode) {
        List<Ward> wards = wardRepository.findAllByDistrict_DistrictCode(districtCode)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phường xã nào có mã quận huyện là " + districtCode));
        List<WardDTO> wardDTOs = new ArrayList<>();
        if (!wards.isEmpty()) {
            wardDTOs = WardDTO.convertEntitiesToDTOs(wards);
        }
        ListWardResponse response = new ListWardResponse();
        response.setWardDTOs(wardDTOs);
        response.setCount(response.getWardDTOs().size());
        response.setDistrictCode(districtCode);
        response.setCode(200);
        response.setMessage("Lấy danh sách phường xã thành công.");
        response.setStatus("OK");
        return response;

    }

}
