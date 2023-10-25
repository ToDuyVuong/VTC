package hcmute.tlcn.vtc.model.dto;

import hcmute.tlcn.vtc.model.entity.Shop;
import hcmute.tlcn.vtc.model.extra.Status;
import lombok.*;

import java.util.regex.Pattern;

@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class ShopDTO {

    private Long shopId;

    private String name;

    private String address;

    private String phone;

    private String email;

    private String avatar;

    private String description;

    private String openTime;

    private String closeTime;

    private Status status;

    private CustomerDTO customerDTO;


    public static ShopDTO convertEntityToDTO(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setShopId(shop.getShopId());
        shopDTO.setName(shop.getName());
        shopDTO.setAddress(shop.getAddress());
        shopDTO.setPhone(shop.getPhone());
        shopDTO.setEmail(shop.getEmail());
        shopDTO.setAvatar(shop.getAvatar());
        shopDTO.setDescription(shop.getDescription());
        shopDTO.setOpenTime(shop.getOpenTime());
        shopDTO.setCloseTime(shop.getCloseTime());
        shopDTO.setStatus(shop.getStatus());
        shopDTO.setCustomerDTO(CustomerDTO.convertEntityToDTO(shop.getCustomer()));
        return shopDTO;
    }
}
