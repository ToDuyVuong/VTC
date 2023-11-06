package hcmute.tlcn.vtc.model.dto;

import hcmute.tlcn.vtc.model.entity.Cart;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private Long cartId;

    private int quantity;

    private Long productId;

    private String productName;

    private String productImage;

    private LocalDateTime updateAt;

    private ProductVariantDTO productVariantDTO;


    public static CartDTO convertEntityToDTO(Cart cart) {
        ProductDTO productDTO = ProductDTO.convertEntityToDTO(cart.getProductVariant().getProduct());
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cart.getCartId());
        cartDTO.setQuantity(cart.getQuantity());
        cartDTO.setProductId(productDTO.getProductId());
        cartDTO.setProductName(productDTO.getName());
        cartDTO.setProductVariantDTO(ProductVariantDTO.convertEntityToDTO(cart.getProductVariant()));
        cartDTO.setUpdateAt(cart.getUpdateAt());

        return cartDTO;
    }


    public static List<CartDTO> convertToListDTO(List<Cart> carts) {
        List<CartDTO> cartDTOs = new ArrayList<>();
        for (Cart cart : carts) {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setCartId(cart.getCartId());
            cartDTO.setQuantity(cart.getQuantity());
            cartDTO.setUpdateAt(cart.getUpdateAt());
            cartDTO.setProductVariantDTO(ProductVariantDTO.convertEntityToDTO(cart.getProductVariant()));

            cartDTO.setProductId(cart.getProductVariant().getProduct().getProductId());
            cartDTO.setProductName(cart.getProductVariant().getProduct().getName());
            cartDTO.setProductImage(cart.getProductVariant().getProduct().getImage());


            cartDTOs.add(cartDTO);
        }
        cartDTOs.sort(Comparator.comparing(CartDTO::getUpdateAt).reversed());

        return cartDTOs;
    }
}
