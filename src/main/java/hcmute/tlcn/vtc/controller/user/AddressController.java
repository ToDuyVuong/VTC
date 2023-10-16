package hcmute.tlcn.vtc.controller.user;

import hcmute.tlcn.vtc.model.dto.user.request.AddressRequest;
import hcmute.tlcn.vtc.model.dto.user.request.AddressStatusRequest;
import hcmute.tlcn.vtc.model.dto.user.response.AddressResponse;
import hcmute.tlcn.vtc.model.dto.user.response.ListAddressResponse;
import hcmute.tlcn.vtc.service.user.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer/address")
@RequiredArgsConstructor
public class AddressController {

    @Autowired
    private final IAddressService addressService;


    @PostMapping("/add")
    public ResponseEntity<AddressResponse> addNewAddressByUsername(@RequestBody AddressRequest request) {
        request.validate();
        AddressResponse response = addressService.addNewAddress(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{addressId}")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable("addressId") String addressId, @RequestParam("username") String username) {
        AddressResponse response = addressService.getAddressById(addressId, username);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/update")
    public ResponseEntity<AddressResponse> updateAddressByUsername(@RequestBody AddressRequest request) {
        AddressResponse response = addressService.updateAddress(request);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/update/status")
    public ResponseEntity<AddressResponse> updateStatusAddressByUsername(@RequestBody AddressStatusRequest request) {
        AddressResponse response = addressService.updateStatusAddress(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/all")
    public ResponseEntity<ListAddressResponse> getAllAddressByUsername(@RequestParam("username") String username) {
        ListAddressResponse response = addressService.getAllAddress(username);
        return ResponseEntity.ok(response);
    }


}
