package hcmute.tlcn.vtc.controller.user;

import hcmute.tlcn.vtc.model.dto.user.request.AddressRequest;
import hcmute.tlcn.vtc.model.dto.user.request.AddressStatusRequest;
import hcmute.tlcn.vtc.model.dto.user.response.AddressResponse;
import hcmute.tlcn.vtc.model.dto.user.response.ListAddressResponse;
import hcmute.tlcn.vtc.service.user.IAddressService;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<AddressResponse> addNewAddressByUsername(@RequestBody AddressRequest addressRequest,
                                                                   HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        addressRequest.setUsername(username);
        addressRequest.validate();
        AddressResponse response = addressService.addNewAddress(addressRequest);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{addressId}")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable("addressId") String addressId,
                                                          HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        AddressResponse response = addressService.getAddressById(addressId, username);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/update")
    public ResponseEntity<AddressResponse> updateAddressByUsername(@RequestBody AddressRequest addressRequest,
                                                                   HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        addressRequest.setUsername(username);
        AddressResponse response = addressService.updateAddress(addressRequest);
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/update/status")
    public ResponseEntity<AddressResponse> updateStatusAddressByUsername(@RequestBody AddressStatusRequest addressStatusRequest,
                                                                         HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        addressStatusRequest.setUsername(username);
        AddressResponse response = addressService.updateStatusAddress(addressStatusRequest);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/all")
    public ResponseEntity<ListAddressResponse> getAllAddressByUsername(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        ListAddressResponse response = addressService.getAllAddress(username);
        return ResponseEntity.ok(response);
    }


}
