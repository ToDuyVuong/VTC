package hcmute.tlcn.vtc.controller.location;

import hcmute.tlcn.vtc.model.data.location.ListDistrictResponse;
import hcmute.tlcn.vtc.service.location.IDistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/location/district")
@RequiredArgsConstructor
public class DistrictController {

    private final IDistrictService districtService;

    @GetMapping("/get-all-by-province-code/{provinceCode}")
    public ResponseEntity<ListDistrictResponse> getAllDistrictByProvinceCode(@PathVariable String provinceCode) {
        if (provinceCode == null || provinceCode.isEmpty()) {
            throw new IllegalArgumentException("Mã tỉnh thành phố không được để trống.");
        }
        ListDistrictResponse response = districtService.getAllDistrictByProvinceCode(provinceCode);
        return ResponseEntity.ok(response);
    }
}
