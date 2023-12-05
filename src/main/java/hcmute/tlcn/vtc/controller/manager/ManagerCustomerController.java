package hcmute.tlcn.vtc.controller.manager;

import hcmute.tlcn.vtc.model.data.manager.response.ListCustomerManagerResponse;
import hcmute.tlcn.vtc.model.data.user.response.ProfileCustomerResponse;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.service.manager.IManagerCustomerService;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manager/customer")
@RequiredArgsConstructor
public class ManagerCustomerController {

    @Autowired
    private IManagerCustomerService managerCustomerService;


    @GetMapping("/list/by-status/{status}")
    public ResponseEntity<ListCustomerManagerResponse> getListCustomerByStatus(@RequestParam int page,
                                                                               @RequestParam int size,
                                                                               @PathVariable Status status) {
        managerCustomerService.checkRequestPageParams(page, size);

        return ResponseEntity.ok(managerCustomerService.getListCustomerByStatus(size, page, status));
    }


    @GetMapping("/list/by-status/{status}/sort")
    public ResponseEntity<ListCustomerManagerResponse> getListCustomerByStatusSort(@RequestParam int page,
                                                                                   @RequestParam int size,
                                                                                   @PathVariable Status status,
                                                                                   @RequestParam String sort) {
        managerCustomerService.checkRequestPageParams(page, size);
        managerCustomerService.checkRequestSortParams(sort);

        return ResponseEntity.ok(managerCustomerService.getListCustomerByStatusSort(size, page, status, sort));
    }


    @GetMapping("/search/status/{status}")
    public ResponseEntity<ListCustomerManagerResponse> searchCustomerByStatus(@RequestParam int page,
                                                                              @RequestParam int size,
                                                                              @PathVariable Status status,
                                                                              @RequestParam String search) {
        managerCustomerService.checkRequestPageParams(page, size);
        if (search == null) {
            throw new IllegalArgumentException("Từ khóa tìm kiếm không được để trống!");
        }

        return ResponseEntity.ok(managerCustomerService.searchCustomerByStatus(size, page, status, search));
    }


    @GetMapping("/detail/{customerId}")
    public ResponseEntity<ProfileCustomerResponse> getCustomerDetailByCustomerId(@PathVariable Long customerId) {
        if (customerId == null) {
            throw new NotFoundException("Mã khách hàng không được để trống!");
        }

        return ResponseEntity.ok(managerCustomerService.getCustomerDetailByCustomerId(customerId));
    }


}

