package hcmute.tlcn.vtc.service.impl;

import hcmute.tlcn.vtc.service.IAdminService;
import hcmute.tlcn.vtc.util.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements IAdminService {


    @Override
    public String getAdmin(String username) {

        if (username.equals("admin")) {
            return "admin";
        }else {
            throw  new NotFoundException("Not found");
        }

    }


}
