package hcmute.tlcn.vtc.service;

import hcmute.tlcn.vtc.dto.user.request.AddressRequest;
import hcmute.tlcn.vtc.dto.user.response.AddressResponse;

public interface IAddressService {
    AddressResponse addNewAddress(AddressRequest request);

    AddressResponse updateAddress(AddressRequest request);
}
