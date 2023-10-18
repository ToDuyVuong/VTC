package hcmute.tlcn.vtc.service.vendor;

import hcmute.tlcn.vtc.model.dto.vendor.request.AttributeRequest;
import hcmute.tlcn.vtc.model.dto.vendor.response.AttributeResponse;
import hcmute.tlcn.vtc.model.dto.vendor.response.ListAttributeResponse;
import hcmute.tlcn.vtc.model.entity.Attribute;

import java.util.List;

public interface IAttributeService {
    AttributeResponse addNewAttribute(AttributeRequest attributeRequest);

    AttributeResponse getAttributeById(Long attributeId, Long shopId);

    ListAttributeResponse getListAttributeByShopId(Long shopId);

    AttributeResponse updateAttribute(AttributeRequest attributeRequest);

    AttributeResponse lockOrActiveAttribute(Long attributeId, Long shopId, boolean active);

    AttributeResponse deleteAttribute(Long attributeId, Long shopId);

    List<Attribute> getListAttributeByListId(List<Long> attributeIds);
}
