package hcmute.tlcn.vtc.service.admin.impl;

import hcmute.tlcn.vtc.model.data.manager.response.ManagerResponse;
import hcmute.tlcn.vtc.model.dto.ManagerDTO;
import hcmute.tlcn.vtc.model.entity.vtc.Customer;
import hcmute.tlcn.vtc.model.entity.vtc.manager.Manager;
import hcmute.tlcn.vtc.model.extra.Role;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.repository.CustomerRepository;
import hcmute.tlcn.vtc.repository.manager.ManagerRepository;
import hcmute.tlcn.vtc.service.admin.IManagerAdminService;
import hcmute.tlcn.vtc.service.user.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ManagerAdminServiceImpl implements IManagerAdminService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private ManagerRepository managerRepository;


    @Override
    @Transactional
    public ManagerResponse addRoleManager(String username, String usernameCustomer) {

        if (managerRepository.existsByManagerUsername(usernameCustomer)) {
            throw new IllegalArgumentException("Nhân viên đã có quyền quản lý!");
        }

        Customer admin = customerService.getCustomerByUsername(username);
        Customer customer = customerService.getCustomerByUsername(usernameCustomer);
        customer.getRoles().add(Role.MANAGER);

        Manager manager = new Manager();
        manager.setAdmin(admin);
        manager.setManager(customer);
        manager.setCreateAt(LocalDateTime.now());
        manager.setUpdateAt(LocalDateTime.now());
        manager.setStatus(Status.ACTIVE);

        try {
            managerRepository.save(manager);
            customerRepository.save(customer);
            ManagerResponse managerResponse = new ManagerResponse();
            managerResponse.setManagerDTO(ManagerDTO.convertEntityToDTO(manager));
            managerResponse.setStatus("OK");
            managerResponse.setMessage("Thêm quyền quản lý cho nhân viên thành công!");

            return managerResponse;
        } catch (Exception e) {
            throw new IllegalArgumentException("Thêm quyền quản lý thất bại!");
        }
    }


}
