package hcmute.tlcn.vtc.service.user;

import hcmute.tlcn.vtc.model.data.user.request.AddressRequest;
import hcmute.tlcn.vtc.model.data.user.request.AddressStatusRequest;
import hcmute.tlcn.vtc.model.data.user.response.AddressResponse;
import hcmute.tlcn.vtc.model.data.user.response.ListAddressResponse;
import hcmute.tlcn.vtc.model.entity.vtc.Address;

public interface IAddressService {
    AddressResponse addNewAddress(AddressRequest request);

    AddressResponse getAddressById(String addressId, String username);

    AddressResponse updateAddress(AddressRequest request);


    AddressResponse updateStatusAddress(AddressStatusRequest request);

    ListAddressResponse getAllAddress(String username);

    Address getAddressActiveByUsername(String username);

    Address getAddressByIdAndUsername(Long addressId, String username);
}
