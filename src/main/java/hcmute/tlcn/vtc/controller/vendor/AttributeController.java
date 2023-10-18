package hcmute.tlcn.vtc.controller.vendor;


import hcmute.tlcn.vtc.model.dto.vendor.request.AttributeRequest;
import hcmute.tlcn.vtc.model.dto.vendor.response.AttributeResponse;
import hcmute.tlcn.vtc.service.vendor.IAttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
