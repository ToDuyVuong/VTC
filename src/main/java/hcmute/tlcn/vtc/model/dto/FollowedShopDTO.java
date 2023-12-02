package hcmute.tlcn.vtc.model.dto;

import hcmute.tlcn.vtc.model.entity.vtc.FollowedShop;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FollowedShopDTO {

    private Long followedShopId;

    private Long shopId;

    private String shopName;

    private String avatar;

    public static List<FollowedShopDTO> convertToListDTO(List<FollowedShop> followedShops) {
        List<FollowedShopDTO> followedShopDTOs = new ArrayList<>();
        if (followedShops != null && !followedShops.isEmpty()) {
            for (FollowedShop followedShop : followedShops) {
                FollowedShopDTO followedShopDTO = new FollowedShopDTO();
                followedShopDTO.setFollowedShopId(followedShop.getFollowedShopId());
                followedShopDTO.setShopId(followedShop.getShop().getShopId());
                followedShopDTO.setShopName(followedShop.getShop().getName());
                followedShopDTO.setAvatar(followedShop.getShop().getAvatar());
                followedShopDTOs.add(followedShopDTO);
            }
        }
        return followedShopDTOs;
    }

}
