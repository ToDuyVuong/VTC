package hcmute.tlcn.vtc.controller.location;

import hcmute.tlcn.vtc.service.location.IAdministrativeUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/location/administrative-unit")
@RequiredArgsConstructor
public class AdministrativeUnitController {

    private final IAdministrativeUnitService administrativeUnitService;

}
