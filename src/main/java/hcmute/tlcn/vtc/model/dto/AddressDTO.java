package hcmute.tlcn.vtc.model.dto;

import hcmute.tlcn.vtc.model.entity.vtc.Address;
import hcmute.tlcn.vtc.model.extra.Status;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Long addressId;

    private String province;

    private String district;

    private String ward;

    private String fullAddress;

    private String fullName;

    private String phone;

    private Status status;



//    public static Address convertToEntity(AddressDTO addressDTO) {
//        ModelMapper modelMapper = new ModelMapper();
//        return modelMapper.map(addressDTO, Address.class);
//    }

    public static AddressDTO convertEntityToDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressId(address.getAddressId());
        addressDTO.setProvince(address.getProvince());
        addressDTO.setDistrict(address.getDistrict());
        addressDTO.setWard(address.getWard());
        addressDTO.setFullAddress(address.getFullAddress());
        addressDTO.setFullName(address.getFullName());
        addressDTO.setPhone(address.getPhone());
        addressDTO.setStatus(address.getStatus());
        return addressDTO;

    }

    public static List<AddressDTO> convertToListDTO(List<Address> addresses) {
        List<AddressDTO> addressDTOs = new ArrayList<>();
        for (Address address : addresses) {
            AddressDTO addressDTO = convertEntityToDTO(address);
            addressDTOs.add(addressDTO);
        }

        return addressDTOs;
    }

    public static Address convertDTOToEntity(AddressDTO addressDTO) {
        Address address = new Address();
        address.setAddressId(addressDTO.getAddressId());
        address.setProvince(addressDTO.getProvince());
        address.setDistrict(addressDTO.getDistrict());
        address.setWard(addressDTO.getWard());
        address.setFullAddress(addressDTO.getFullAddress());
        address.setFullName(addressDTO.getFullName());
        address.setPhone(addressDTO.getPhone());
        address.setStatus(addressDTO.getStatus());
        return address;
    }


}
