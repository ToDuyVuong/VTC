package hcmute.tlcn.vtc.service.location;

import hcmute.tlcn.vtc.model.data.location.ListWardResponse;

public interface IWardService {
    ListWardResponse getAllWardByDistrictCode(String districtCode);
}
