package hcmute.tlcn.vtc.controller.vendor;


import hcmute.tlcn.vtc.model.data.vendor.request.AttributeRequest;
import hcmute.tlcn.vtc.model.data.vendor.response.AttributeResponse;
import hcmute.tlcn.vtc.model.data.vendor.response.ListAttributeResponse;
import hcmute.tlcn.vtc.service.vendor.IAttributeShopService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor/attribute")
@RequiredArgsConstructor
public class AttributeShopController {

    @Autowired
    private IAttributeShopService attributeService;

    @PostMapping("/add")
    public ResponseEntity<AttributeResponse> addNewAttribute(AttributeRequest attributeRequest,
                                                             HttpServletRequest httpServletRequest) {

        String username = (String) httpServletRequest.getAttribute("username");
        attributeRequest.setUsername(username);
        attributeRequest.validate();
        AttributeResponse response = attributeService.addNewAttribute(attributeRequest);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{attributeId}")
    public ResponseEntity<AttributeResponse> getAttributeById(@PathVariable Long attributeId,
                                                              HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        if (attributeId == null) {
            throw new NullPointerException("Mã thuộc tính không được để trống!");
        }

        AttributeResponse response = attributeService.getAttributeById(attributeId, username);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/all")
    public ResponseEntity<ListAttributeResponse> getListAttributeByShopId(HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        ListAttributeResponse response = attributeService.getListAttributeByShopId(username);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/update")
    public ResponseEntity<AttributeResponse> updateAttribute(AttributeRequest attributeRequest,
                                                             HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        attributeRequest.setUsername(username);
        attributeRequest.validateUpdate();
        AttributeResponse response = attributeService.updateAttribute(attributeRequest);
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/lock")
    public ResponseEntity<AttributeResponse> lockAttribute(@RequestParam Long attributeId,
                                                           @RequestParam boolean active,
                                                           HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");

        if (attributeId == null) {
            throw new NullPointerException("Mã thuộc tính không được để trống!");
        }

        AttributeResponse response = attributeService.lockOrActiveAttribute(attributeId, username, active);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<AttributeResponse> deleteAttribute(@RequestParam Long attributeId,
                                                             HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        if (attributeId == null) {
            throw new NullPointerException("Mã thuộc tính không được để trống!");
        }

        AttributeResponse response = attributeService.deleteAttribute(attributeId, username);
        return ResponseEntity.ok(response);
    }
}
