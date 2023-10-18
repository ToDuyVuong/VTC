package hcmute.tlcn.vtc.controller.vendor;


import hcmute.tlcn.vtc.model.dto.vendor.request.AttributeRequest;
import hcmute.tlcn.vtc.model.dto.vendor.response.AttributeResponse;
import hcmute.tlcn.vtc.model.dto.vendor.response.ListAttributeResponse;
import hcmute.tlcn.vtc.service.vendor.IAttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor/attribute")
@RequiredArgsConstructor
public class AttributeController {

    @Autowired
    private IAttributeService attributeService;

    @PostMapping("/add")
    public ResponseEntity<AttributeResponse> addNewAttribute(AttributeRequest attributeRequest) {
        attributeRequest.validate();
        AttributeResponse response = attributeService.addNewAttribute(attributeRequest);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{attributeId}")
    public ResponseEntity<AttributeResponse> getAttributeById(@PathVariable Long attributeId, @RequestParam Long shopId) {
        if (attributeId == null) {
            throw new NullPointerException("Mã thuộc tính không được để trống!");
        }
        if (shopId == null) {
            throw new NullPointerException("Mã cửa hàng không được để trống!");
        }

        AttributeResponse response = attributeService.getAttributeById(attributeId, shopId);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/all")
    public ResponseEntity<ListAttributeResponse> getListAttributeByShopId(@RequestParam Long shopId) {
        if (shopId == null) {
            throw new NullPointerException("Mã cửa hàng không được để trống!");
        }

        ListAttributeResponse response = attributeService.getListAttributeByShopId(shopId);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/update")
    public ResponseEntity<AttributeResponse> updateAttribute(AttributeRequest attributeRequest) {
        attributeRequest.validateUpdate();
        AttributeResponse response = attributeService.updateAttribute(attributeRequest);
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/lock")
    public ResponseEntity<AttributeResponse> lockAttribute(@RequestParam Long attributeId,
                                                           @RequestParam Long shopId,
                                                           @RequestParam boolean active) {
        if (attributeId == null) {
            throw new NullPointerException("Mã thuộc tính không được để trống!");
        }
        if (shopId == null) {
            throw new NullPointerException("Mã cửa hàng không được để trống!");
        }

        AttributeResponse response = attributeService.lockOrActiveAttribute(attributeId, shopId, active);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<AttributeResponse> deleteAttribute(@RequestParam Long attributeId, @RequestParam Long shopId) {
        if (attributeId == null) {
            throw new NullPointerException("Mã thuộc tính không được để trống!");
        }
        if (shopId == null) {
            throw new NullPointerException("Mã cửa hàng không được để trống!");
        }

        AttributeResponse response = attributeService.deleteAttribute(attributeId, shopId);
        return ResponseEntity.ok(response);
    }
}
