package hcmute.tlcn.vtc.model.data.dto;

import hcmute.tlcn.vtc.model.entity.Cart;
import hcmute.tlcn.vtc.model.entity.Shop;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListCartByShopDTO {

    private Long shopId;

    private String shopName;

    private String avatar;

    private List<CartDTO> carts;

    public static List<ListCartByShopDTO> convertToListDTOByShop(List<Cart> carts) {
        carts.sort(Comparator.comparing(Cart::getUpdateAt));

        Map<Shop, List<Cart>> groupedAndSortedCarts = carts.stream()
                .collect(Collectors.groupingBy(cart -> cart.getProductVariant().getProduct().getCategory().getShop(),
                        Collectors.toList()));

        List<ListCartByShopDTO> listCartByShopDTOs = new ArrayList<>();

        for (Map.Entry<Shop, List<Cart>> entry : groupedAndSortedCarts.entrySet()) {
            Shop shop = entry.getKey();
            List<CartDTO> cartDTOs = CartDTO.convertToListDTO(entry.getValue());

            ListCartByShopDTO listCartByShopDTO = new ListCartByShopDTO();
            listCartByShopDTO.setShopId(shop.getShopId());
            listCartByShopDTO.setShopName(shop.getName());
            listCartByShopDTO.setAvatar(shop.getAvatar());
            listCartByShopDTO.setCarts(cartDTOs);

            listCartByShopDTOs.add(listCartByShopDTO);
        }

        listCartByShopDTOs.sort((dto1, dto2) -> {
            LocalDateTime lastUpdateAt1 = dto1.getCarts().stream()
                    .map(CartDTO::getUpdateAt)
                    .max(LocalDateTime::compareTo)
                    .orElse(LocalDateTime.MIN);

            LocalDateTime lastUpdateAt2 = dto2.getCarts().stream()
                    .map(CartDTO::getUpdateAt)
                    .max(LocalDateTime::compareTo)
                    .orElse(LocalDateTime.MIN);

            return lastUpdateAt2.compareTo(lastUpdateAt1);
        });

        return listCartByShopDTOs;
    }
}
