package hcmute.tlcn.vtc.model.dto;


import hcmute.tlcn.vtc.model.entity.Voucher;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.model.extra.VoucherType;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDTO {

    private Long voucherId;

    private Status status;

    private String code;

    private String name;

    private String description;

    private Long discount;

    private Long minPrice;

    private Long maxPrice;

    private Long maxDiscount;

    private Long quantity;

    private Date startDate;

    private Date endDate;

    private Long quantityUsed;

    private String type;


    public static VoucherDTO convertEntityToDTO(Voucher voucher) {
        VoucherDTO voucherDTO = new VoucherDTO();
        voucherDTO.setVoucherId(voucher.getVoucherId());
        voucherDTO.setStatus(voucher.getStatus());
        voucherDTO.setCode(voucher.getCode());
        voucherDTO.setName(voucher.getName());
        voucherDTO.setDescription(voucher.getDescription());
        voucherDTO.setDiscount(voucher.getDiscount());
        voucherDTO.setMinPrice(voucher.getMinPrice());
        voucherDTO.setMaxPrice(voucher.getMaxPrice());
        voucherDTO.setMaxDiscount(voucher.getMaxDiscount());
        voucherDTO.setQuantity(voucher.getQuantity());
        voucherDTO.setStartDate(voucher.getStartDate());
        voucherDTO.setEndDate(voucher.getEndDate());
        voucherDTO.setQuantityUsed(voucher.getQuantityUsed());

        if(voucher.getType() == null || voucher.getType().equals(VoucherType.PERCENTAGE_SHOP) ){
            voucherDTO.setType("Giảm theo phần trăm");
        }else{
            voucherDTO.setType("Giảm theo tiền");
        }

        return voucherDTO;
    }


    public static List<VoucherDTO> convertToListDTO(List<Voucher> vouchers) {
        List<VoucherDTO> voucherDTOS = new ArrayList<>();
        for (Voucher voucher : vouchers) {
            voucherDTOS.add(convertEntityToDTO(voucher));
        }

        voucherDTOS.sort(Comparator.comparing(VoucherDTO::getStartDate).reversed());


        return voucherDTOS;
    }

    public String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            code.append(characters.charAt(random.nextInt(characters.length())));
        }
        return code.toString();
    }

}


