package hcmute.tlcn.vtc.service.vendor.impl;

import hcmute.tlcn.vtc.model.dto.AttributeDTO;
import hcmute.tlcn.vtc.model.dto.vendor.request.AttributeRequest;
import hcmute.tlcn.vtc.model.dto.vendor.response.AttributeResponse;
import hcmute.tlcn.vtc.model.dto.vendor.response.ListAttributeResponse;
import hcmute.tlcn.vtc.model.entity.Attribute;
import hcmute.tlcn.vtc.model.entity.Shop;
import hcmute.tlcn.vtc.repository.*;
import hcmute.tlcn.vtc.service.user.impl.CustomerServiceImpl;
import hcmute.tlcn.vtc.service.vendor.IAttributeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

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
        attribute.setActive(true);
        attribute.setCreateAt(LocalDateTime.now());
        attribute.setUpdateAt(LocalDateTime.now());

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

    @Override
    public AttributeResponse getAttributeById(Long attributeId, Long shopId) {
        Attribute attribute = checkAttributeInShop(attributeId, shopId);

        AttributeResponse attributeResponse = new AttributeResponse();
        attributeResponse.setAttributeDTO(modelMapper.map(attribute, AttributeDTO.class));
        attributeResponse.setStatus("ok");
        attributeResponse.setMessage("Lấy thông tin thuộc tính thành công!");
        attributeResponse.setCode(200);

        return attributeResponse;
    }

    @Override
    public ListAttributeResponse getListAttributeByShopId(Long shopId) {
        checkShop(shopId);
        List<Attribute> attributes = attributeRepository.findAllByShop_ShopIdAndActive(shopId, true);
        if (attributes.isEmpty()) {
            throw new IllegalArgumentException("Cửa hàng không có thuộc tính!");
        }
        attributes.sort(Comparator.comparing(Attribute::getName).thenComparing(Attribute::getValue));
        List<AttributeDTO> attributeDTOs = AttributeDTO.convertToListDTO(attributes);

        ListAttributeResponse response = new ListAttributeResponse();
        response.setAttributeDTOs(attributeDTOs);
        response.setStatus("ok");
        response.setMessage("Lấy danh sách thuộc tính trong cửa hàng thành công!");
        response.setCode(200);

        return response;
    }

    @Override
    public AttributeResponse updateAttribute(AttributeRequest attributeRequest) {
        Attribute attribute = checkAttributeInShop(attributeRequest.getAttributeId(), attributeRequest.getShopId());
        existsAttribute(attributeRequest.getName(), attributeRequest.getValue(), attributeRequest.getShopId());

        attribute.setName(attributeRequest.getName());
        attribute.setValue(attributeRequest.getValue());
        attribute.setUpdateAt(LocalDateTime.now());

        try {
            attributeRepository.save(attribute);

            AttributeResponse attributeResponse = new AttributeResponse();
            attributeResponse.setAttributeDTO(modelMapper.map(attribute, AttributeDTO.class));
            attributeResponse.setStatus("success");
            attributeResponse.setMessage("Cập nhật thuộc tính thành công!");
            attributeResponse.setCode(200);

            return attributeResponse;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cập nhật thuộc tính thất bại!");
        }
    }

    @Override
    public AttributeResponse lockOrActiveAttribute(Long attributeId, Long shopId, boolean active) {
        Attribute attribute = checkAttributeInShop(attributeId, shopId);
        if (!active && attribute.isUsedInProductVariants()) {
            throw new IllegalArgumentException("Thuộc tính đã được sử dụng trong sản phẩm nên không thể khóa!");
        }
        attribute.setActive(active);
        attribute.setUpdateAt(LocalDateTime.now());
        String message = active ? "Mở khóa" : "Khóa";

        try {
            attributeRepository.save(attribute);

            AttributeResponse attributeResponse = new AttributeResponse();
            attributeResponse.setAttributeDTO(modelMapper.map(attribute, AttributeDTO.class));
            attributeResponse.setStatus("success");
            attributeResponse.setMessage(message + " thuộc tính trong cửa hàng thành công.");
            attributeResponse.setCode(200);

            return attributeResponse;
        } catch (Exception e) {
            throw new IllegalArgumentException(message + " thuộc tính thất bại!");
        }
    }

    @Override
    public AttributeResponse deleteAttribute(Long attributeId, Long shopId) {
        Attribute attribute = checkAttributeInShop(attributeId, shopId);
        if (attribute.isUsedInProductVariants()) {
            throw new IllegalArgumentException("Thuộc tính đã được sử dụng trong sản phẩm nên không thể xóa!");
        }

        try {
            attributeRepository.delete(attribute);

            AttributeResponse attributeResponse = new AttributeResponse();
            attributeResponse.setAttributeDTO(modelMapper.map(attribute, AttributeDTO.class));
            attributeResponse.setStatus("success");
            attributeResponse.setMessage("Xóa thuộc tính trong cửa hàng thành công.");
            attributeResponse.setCode(200);

            return attributeResponse;
        } catch (Exception e) {
            throw new IllegalArgumentException("Xóa thuộc tính thất bại trong cửa hàng!");
        }
    }

    private Attribute checkAttributeInShop(Long attributeId, Long shopId) {
        Attribute attribute = attributeRepository.findById(attributeId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy thuộc tính!"));

        if (!attribute.getShop().getShopId().equals(shopId)) {
            throw new IllegalArgumentException("Không tìm thấy thuộc tính trong cửa hàng!");
        }
        return attribute;
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
