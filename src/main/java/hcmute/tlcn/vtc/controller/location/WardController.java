package hcmute.tlcn.vtc.controller.location;

import hcmute.tlcn.vtc.model.data.location.ListWardResponse;
import hcmute.tlcn.vtc.service.location.IWardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/location/ward")
@RequiredArgsConstructor
public class WardController {

    private final IWardService wardService;

    @GetMapping("/get-all-by-district-code/{districtCode}")
    public ResponseEntity<ListWardResponse> getAllWardByDistrictCode(@PathVariable String districtCode) {
        if (districtCode == null || districtCode.isEmpty()) {
            throw new IllegalArgumentException("Mã quận huyện không được để trống.");
        }
        ListWardResponse response = wardService.getAllWardByDistrictCode(districtCode);
        return ResponseEntity.ok(response);
    }
}
