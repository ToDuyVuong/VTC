package hcmute.tlcn.vtc.service.vendor.impl;

import hcmute.tlcn.vtc.model.dto.AttributeDTO;
import hcmute.tlcn.vtc.model.dto.vendor.request.AttributeRequest;
import hcmute.tlcn.vtc.model.dto.vendor.response.AttributeResponse;
import hcmute.tlcn.vtc.model.entity.Attribute;
import hcmute.tlcn.vtc.model.entity.Shop;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.repository.*;
import hcmute.tlcn.vtc.service.user.impl.CustomerServiceImpl;
import hcmute.tlcn.vtc.service.vendor.IAttributeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AttributeServiceImpl implements IAttributeService {

    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public AttributeResponse addNewAttribute(AttributeRequest attributeRequest) {

        Shop shop = checkShop(attributeRequest.getShopId());
        existsAttribute(attributeRequest.getName(), attributeRequest.getValue(), attributeRequest.getShopId());

        Attribute attribute = new Attribute();
        attribute.setName(attributeRequest.getName());
        attribute.setValue(attributeRequest.getValue());
        attribute.setShop(shop);
        attribute.setStatus(Status.ACTIVE);
        attribute.setAtCreate(LocalDateTime.now());
        attribute.setAtUpdate(LocalDateTime.now());

        try {
            attributeRepository.save(attribute);

            AttributeResponse attributeResponse = new AttributeResponse();
            attributeResponse.setAttributeDTO(modelMapper.map(attribute, AttributeDTO.class));
            attributeResponse.setStatus("success");
            attributeResponse.setMessage("Thêm thuộc tính trong cửa hàng thành công!");
            attributeResponse.setCode(200);

            return attributeResponse;
        } catch (Exception e) {
            throw new IllegalArgumentException("Thêm thuộc tính thất bại!");
        }

    }






    public Shop checkShop(Long shopId) {
        return shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy cửa hàng!"));
    }


    public void existsAttribute(String name, String value, Long shopId) {
        boolean existsAttribute = attributeRepository.existsByNameAndValueAndShop_ShopId(
                name,
                value,
                shopId);
        if (existsAttribute) {
            throw new IllegalArgumentException("Thuộc tính đã tồn tại trong cửa hàng!");
        }
    }


}
