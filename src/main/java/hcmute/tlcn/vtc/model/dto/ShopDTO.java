package hcmute.tlcn.vtc.model.dto;

import hcmute.tlcn.vtc.model.entity.vtc.Shop;
import hcmute.tlcn.vtc.model.extra.Status;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class ShopDTO {

    private Long shopId;

    private String name;

    private String address;

    private String province;

    private String district;

    private String ward;

    private String phone;

    private String email;

    private String avatar;

    private String description;

    private String openTime;

    private String closeTime;

    private Status status;

    private Long customerId;

    //  private CustomerDTO customerDTO;


    public static ShopDTO convertEntityToDTO(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setShopId(shop.getShopId());
        shopDTO.setName(shop.getName());
        shopDTO.setAddress(shop.getAddress());
        shopDTO.setProvince(shop.getProvince());
        shopDTO.setDistrict(shop.getDistrict());
        shopDTO.setWard(shop.getWard());
        shopDTO.setPhone(shop.getPhone());
        shopDTO.setEmail(shop.getEmail());
        shopDTO.setAvatar(shop.getAvatar());
        shopDTO.setDescription(shop.getDescription());
        shopDTO.setOpenTime(shop.getOpenTime());
        shopDTO.setCloseTime(shop.getCloseTime());
        shopDTO.setStatus(shop.getStatus());
        shopDTO.setCustomerId(shop.getCustomer().getCustomerId());
        // shopDTO.setCustomerDTO(CustomerDTO.convertEntityToDTO(shop.getCustomer()));
        return shopDTO;
    }

    public static List<ShopDTO> convertEntitiesToDTOs(List<Shop> shops) {
        List<ShopDTO> shopDTOs = new ArrayList<>();
        for (Shop shop : shops) {
            shopDTOs.add(convertEntityToDTO(shop));
        }
        //shopDTOs.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
        return shopDTOs;
    }
}
