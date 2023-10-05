package hcmute.tlcn.vtc.controller.user;

import hcmute.tlcn.vtc.dto.user.request.AddressRequest;
import hcmute.tlcn.vtc.dto.user.response.AddressResponse;
import hcmute.tlcn.vtc.service.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer/address")
@RequiredArgsConstructor
public class AddressController {

    @Autowired
    private final IAddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<AddressResponse> addNewAddress(@RequestBody AddressRequest request) {
        AddressResponse response = addressService.addNewAddress(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<AddressResponse> updateAddress(@RequestBody AddressRequest request) {
        AddressResponse response = addressService.updateAddress(request);
        return ResponseEntity.ok(response);
    }

}
