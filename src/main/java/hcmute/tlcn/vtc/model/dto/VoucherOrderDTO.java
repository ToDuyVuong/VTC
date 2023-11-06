package hcmute.tlcn.vtc.model.dto;

import hcmute.tlcn.vtc.model.entity.VoucherOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class VoucherOrderDTO {

    private Long voucherOrderId;

    private Long voucherId;

    private String voucherName;

    private Long orderId;

    public static VoucherOrderDTO convertEntityToDTO(VoucherOrder voucherOrder){
        VoucherOrderDTO voucherOrderDTO = new VoucherOrderDTO();
        voucherOrderDTO.setVoucherOrderId(voucherOrder.getVoucherOrderId());
        voucherOrderDTO.setVoucherId(voucherOrder.getVoucher().getVoucherId());
        voucherOrderDTO.setVoucherName(voucherOrder.getVoucher().getName());
        voucherOrderDTO.setOrderId(voucherOrder.getOrder().getOrderId());
        return voucherOrderDTO;

    }

    public static List<VoucherOrderDTO> convertListEntityToListDTO(List<VoucherOrder> voucherOrders){
        if (voucherOrders == null){
            return null;
        }

        List<VoucherOrderDTO> voucherOrderDTOs = new ArrayList<>();
        for (VoucherOrder voucherOrder : voucherOrders){
            VoucherOrderDTO voucherOrderDTO =convertEntityToDTO(voucherOrder);
            voucherOrderDTOs.add(voucherOrderDTO);
        }
        return voucherOrderDTOs;
    }
}
