package hcmute.tlcn.vtc.service.admin.impl;

import hcmute.tlcn.vtc.repository.CustomerRepository;
import hcmute.tlcn.vtc.service.admin.IManagerAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerAdminServiceImpl implements IManagerAdminService {

    @Autowired
    private CustomerRepository customerRepository;

//    public Customer

}
