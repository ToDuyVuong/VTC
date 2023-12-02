package hcmute.tlcn.vtc.service.location;

import hcmute.tlcn.vtc.model.data.location.ListDistrictResponse;

public interface IDistrictService {
    ListDistrictResponse getAllDistrictByProvinceCode(String provinceCode);
}
