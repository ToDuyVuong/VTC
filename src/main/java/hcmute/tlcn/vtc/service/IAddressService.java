package hcmute.tlcn.vtc.service;

import hcmute.tlcn.vtc.dto.user.request.AddressRequest;
import hcmute.tlcn.vtc.dto.user.request.AddressStatusRequest;
import hcmute.tlcn.vtc.dto.user.response.AddressResponse;
import hcmute.tlcn.vtc.dto.user.response.ListAddressResponse;

public interface IAddressService {
    AddressResponse addNewAddress(AddressRequest request);

    AddressResponse getAddressById(String addressId, String username);

    AddressResponse updateAddress(AddressRequest request);


    AddressResponse updateStatusAddress(AddressStatusRequest request);

    ListAddressResponse getAllAddress(String username);
}
