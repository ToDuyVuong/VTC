package hcmute.tlcn.vtc.controller.location;

import hcmute.tlcn.vtc.service.location.IAdministrativeRegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/location/administrative-region")
@RequiredArgsConstructor
public class AdministrativeRegionController {

    private final IAdministrativeRegionService administrativeRegionService;

}
