package hcmute.tlcn.vtc.controller.location;

import hcmute.tlcn.vtc.model.data.location.ListProvinceResponse;
import hcmute.tlcn.vtc.service.location.IProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/location/province")
@RequiredArgsConstructor
public class ProvinceController {

    private final IProvinceService provinceService;

    @GetMapping("/get-all")
    public ResponseEntity<ListProvinceResponse> getAllProvince() {
        return ResponseEntity.ok(provinceService.getAllProvince());
    }
}
