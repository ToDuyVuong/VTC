package hcmute.tlcn.vtc.service.location.impl;

import hcmute.tlcn.vtc.model.data.location.ListProvinceResponse;
import hcmute.tlcn.vtc.model.dto.location_dto.ProvinceDTO;
import hcmute.tlcn.vtc.model.entity.location.Province;
import hcmute.tlcn.vtc.repository.location.ProvinceRepository;
import hcmute.tlcn.vtc.service.location.IProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinceServiceImpl implements IProvinceService {
    private final ProvinceRepository provinceRepository;



    @Override
    public ListProvinceResponse getAllProvince() {

        List<Province> provinces = provinceRepository.findAll();
        List<ProvinceDTO> provinceDTOs = new ArrayList<>();
        if (!provinces.isEmpty()) {
            provinceDTOs = ProvinceDTO.convertEntitiesToDTOs(provinces);
        }

        ListProvinceResponse response = new ListProvinceResponse();
        response.setProvinceDTOs(provinceDTOs);
        response.setCount(response.getProvinceDTOs().size());
        response.setCode(200);
        response.setMessage("Lấy danh sách tỉnh thành phố thành công.");
        response.setStatus("OK");
        return response;
    }

}
