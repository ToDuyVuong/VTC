package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.data.user.response.CustomerVoucherResponse;
import hcmute.tlcn.vtc.model.data.user.response.ListCustomerVoucherResponse;

public interface ICustomerVoucherService {
    CustomerVoucherResponse saveVoucher(Long voucherId, String username);

    ListCustomerVoucherResponse listCustomerVoucherByUsername(String username);

    CustomerVoucherResponse deleteCustomerVoucher(Long voucherId, String username);
}
