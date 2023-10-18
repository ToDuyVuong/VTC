package hcmute.tlcn.vtc.service.vendor;

import hcmute.tlcn.vtc.model.dto.vendor.request.AttributeRequest;
import hcmute.tlcn.vtc.model.dto.vendor.response.AttributeResponse;

public interface IAttributeService {
    AttributeResponse addNewAttribute(AttributeRequest attributeRequest);
}
