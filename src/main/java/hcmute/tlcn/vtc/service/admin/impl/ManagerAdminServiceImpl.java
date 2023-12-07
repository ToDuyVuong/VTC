package hcmute.tlcn.vtc.service.admin.impl;

import hcmute.tlcn.vtc.model.data.manager.response.ListManagerPageResponse;
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
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

        if (managerRepository.existsByManagerUsernameAndStatus(usernameCustomer, Status.ACTIVE)) {
            throw new IllegalArgumentException("Nhân viên đã có quyền quản lý!");
        }

        Customer admin = customerService.getCustomerByUsername(username);
        Customer customer = customerService.getCustomerByUsername(usernameCustomer);
        customer.getRoles().add(Role.MANAGER);

        Manager manager = new Manager();
        if (managerRepository.existsByManagerUsername(usernameCustomer)) {
            manager = managerRepository.findByManagerUsername(usernameCustomer).
                    orElseThrow(() -> new NotFoundException("Không tìm thấy quản lý!"));
            if (!manager.getAdmin().equals(admin)) {
                throw new IllegalArgumentException("Bạn không có quyền thêm quyền quản lý cho nhân viên này!");
            }
        } else {
            manager.setAdmin(admin);
            manager.setManager(customer);
            manager.setCreateAt(LocalDateTime.now());
        }
        manager.setUpdateAt(LocalDateTime.now());
        manager.setStatus(Status.ACTIVE);


        try {
            managerRepository.save(manager);
            customerRepository.save(customer);
            ManagerResponse managerResponse = new ManagerResponse();
            managerResponse.setManagerDTO(ManagerDTO.convertEntityToDTO(manager));
            managerResponse.setStatus("OK");
            managerResponse.setCode(200);
            managerResponse.setMessage("Thêm quyền quản lý cho nhân viên thành công!");

            return managerResponse;
        } catch (Exception e) {
            throw new IllegalArgumentException("Thêm quyền quản lý thất bại!" + e.getMessage());
        }
    }


    @Override
    @Transactional
    public ManagerResponse removeRoleManager(String username, String usernameCustomer) {
        if (!managerRepository.existsByManagerUsername(usernameCustomer)) {
            throw new IllegalArgumentException("Nhân viên không có quyền quản lý!");
        }

        Customer admin = customerService.getCustomerByUsername(username);
        Customer customer = customerService.getCustomerByUsername(usernameCustomer);
        customer.getRoles().remove(Role.MANAGER);

        Manager manager = checkManager(usernameCustomer, admin.getCustomerId());
        manager.setStatus(Status.DELETED);
        manager.setUpdateAt(LocalDateTime.now());

        try {
            managerRepository.save(manager);
            customerRepository.save(customer);
            ManagerResponse managerResponse = new ManagerResponse();
            managerResponse.setManagerDTO(ManagerDTO.convertEntityToDTO(manager));
            managerResponse.setStatus("OK");
            managerResponse.setCode(200);
            managerResponse.setMessage("Xóa quyền quản lý cho nhân viên thành công!");

            return managerResponse;
        } catch (Exception e) {
            throw new IllegalArgumentException("Xóa quyền quản lý thất bại!" + e.getMessage());
        }
    }

    @Override
    public ListManagerPageResponse getManagerPage(int page, int size) {
        int totalManager = managerRepository.countAllBy();
        int totalPage = (int) Math.ceil((double) totalManager / size);
        Page<Manager> managerPage = managerRepository.findAllByOrderByManagerFullName(PageRequest.of(page - 1, size)).
                orElseThrow(() -> new NotFoundException("Không tìm thấy danh sách quản lý!"));
        String message = "Lấy danh sách quản lý thành công!";

        return listManagerPageResponse(managerPage.getContent(), totalPage, page, size, message);
    }


    @Override
    public ListManagerPageResponse getManagerPageByUsername(int page, int size, String username) {
        int totalManager = managerRepository.countAllByManagerUsernameContainsAndStatus(username, Status.ACTIVE);
        int totalPage = (int) Math.ceil((double) totalManager / size);
        Page<Manager> managerPage = managerRepository.findAllByManagerUsernameContainingAndStatusOrderByManagerFullName(username, Status.ACTIVE, PageRequest.of(page - 1, size)).
                orElseThrow(() -> new NotFoundException("Không tìm thấy danh sách quản lý!"));
        String message = "Lấy danh sách quản lý thành công!";

        return listManagerPageResponse(managerPage.getContent(), totalPage, page, size, message);
    }


    @Override
    public ListManagerPageResponse getManagerPageByStatus(int page, int size, Status status) {
        int totalManager = managerRepository.countAllByStatus(status);
        int totalPage = (int) Math.ceil((double) totalManager / size);
        Page<Manager> managerPage = managerRepository.findAllByStatusOrderByManagerFullName(status, PageRequest.of(page - 1, size)).
                orElseThrow(() -> new NotFoundException("Không tìm thấy danh sách quản lý!"));
        String message = "Lấy danh sách quản lý theo trạng thái thành công!";

        return listManagerPageResponse(managerPage.getContent(), totalPage, page, size, message);
    }


    @Override
    public void checkRequestPageParams(int page, int size) {
        if (page < 0) {
            throw new NotFoundException("Trang không được nhỏ hơn 0!");
        }
        if (size < 0) {
            throw new NotFoundException("Kích thước trang không được nhỏ hơn 0!");
        }
        if (size > 500) {
            throw new NotFoundException("Kích thước trang không được lớn hơn 200!");
        }
    }


    private Manager checkManager(String usernameCustomer, Long adminId) {
        Manager manager = managerRepository.findByManagerUsername(usernameCustomer).
                orElseThrow(() -> new NotFoundException("Không tìm thấy quản lý!"));


        if (!manager.getAdmin().getCustomerId().equals(adminId)) {
            throw new IllegalArgumentException("Bạn không có quyền xóa quản lý này!");
        }

        if (manager.getStatus() == Status.DELETED) {
            throw new IllegalArgumentException("Nhân viên đã bị xóa quyền quản lý!");
        }

        return manager;
    }


    private ListManagerPageResponse listManagerPageResponse(List<Manager> managers, int totalPage, int page, int size, String message) {
        ListManagerPageResponse listManagerPageResponse = new ListManagerPageResponse();
        listManagerPageResponse.setManagerDTOs(ManagerDTO.convertEntitiesToDTOs(managers));
        listManagerPageResponse.setCount(managers.size());
        listManagerPageResponse.setTotalPage(totalPage);
        listManagerPageResponse.setPage(page);
        listManagerPageResponse.setSize(size);
        listManagerPageResponse.setStatus("OK");
        listManagerPageResponse.setCode(200);
        listManagerPageResponse.setMessage(message);
        return listManagerPageResponse;
    }


}
