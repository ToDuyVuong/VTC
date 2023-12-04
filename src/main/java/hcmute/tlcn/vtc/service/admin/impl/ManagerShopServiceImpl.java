package hcmute.tlcn.vtc.service.admin.impl;

import hcmute.tlcn.vtc.repository.ShopRepository;
import hcmute.tlcn.vtc.service.admin.IManagerProductService;
import hcmute.tlcn.vtc.service.admin.IManagerShopService;
import hcmute.tlcn.vtc.service.user.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerShopServiceImpl implements IManagerShopService {

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IManagerProductService managerProductService;

}
