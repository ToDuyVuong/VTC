package hcmute.tlcn.vtc.service.location.impl;

import hcmute.tlcn.vtc.repository.location.AdministrativeUnitRepository;
import hcmute.tlcn.vtc.service.location.IAdministrativeUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdministrativeUnitServiceImpl implements IAdministrativeUnitService {
    private final AdministrativeUnitRepository administrativeUnitRepository;



}
