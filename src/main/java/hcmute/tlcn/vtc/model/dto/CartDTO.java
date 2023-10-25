package hcmute.tlcn.vtc.model.dto;

import hcmute.tlcn.vtc.model.entity.Cart;
import hcmute.tlcn.vtc.model.entity.Customer;
import hcmute.tlcn.vtc.model.entity.Product;
import hcmute.tlcn.vtc.model.entity.ProductVariant;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private Long cartId;

    private int quantity;

    private ProductVariantDTO productVariantDTO;

    private ProductDTO productDTO;


    public static List<CartDTO> convertToListDTO(List<Cart> carts) {
        List<CartDTO> cartDTOs = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Cart cart : carts) {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setCartId(cart.getCartId());
            cartDTO.setQuantity(cart.getQuantity());
            cartDTO.setProductVariantDTO(modelMapper.map(cart.getProductVariant(), ProductVariantDTO.class));
            cartDTO.setProductDTO(modelMapper.map(cart.getProductVariant().getProduct(), ProductDTO.class));

            cartDTOs.add(cartDTO);
        }

        return cartDTOs;
    }
}
