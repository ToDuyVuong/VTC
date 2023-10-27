package hcmute.tlcn.vtc.service.vendor;

import hcmute.tlcn.vtc.model.data.vendor.request.AttributeRequest;
import hcmute.tlcn.vtc.model.data.vendor.response.AttributeResponse;
import hcmute.tlcn.vtc.model.data.vendor.response.ListAttributeResponse;
import hcmute.tlcn.vtc.model.entity.Attribute;

import java.util.List;

public interface IAttributeShopService {
    AttributeResponse addNewAttribute(AttributeRequest attributeRequest);

    AttributeResponse getAttributeById(Long attributeId, String username);

    ListAttributeResponse getListAttributeByShopId(String username);

    AttributeResponse updateAttribute(AttributeRequest attributeRequest);

    AttributeResponse lockOrActiveAttribute(Long attributeId, String username, boolean active);

    AttributeResponse deleteAttribute(Long attributeId, String username);

    List<Attribute> getListAttributeByListId(List<Long> attributeIds, Long shopId);
}
