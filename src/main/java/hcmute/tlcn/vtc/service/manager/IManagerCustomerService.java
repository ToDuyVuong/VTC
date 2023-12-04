package hcmute.tlcn.vtc.service.manager;

import hcmute.tlcn.vtc.model.data.manager.ListCustomerManagerResponse;
import hcmute.tlcn.vtc.model.data.user.response.ProfileCustomerResponse;
import hcmute.tlcn.vtc.model.extra.Status;

public interface IManagerCustomerService {
    ListCustomerManagerResponse getListCustomerByStatus(int size, int page, Status status);

    ListCustomerManagerResponse getListCustomerByStatusSort(int size, int page, Status status, String sort);

    ListCustomerManagerResponse searchCustomerByStatus(int size, int page, Status status, String search);

    ProfileCustomerResponse getCustomerDetailByCustomerId(Long customerId);

    void checkRequestPageParams(int page, int size);

    void checkRequestSortParams(String sort);
}
