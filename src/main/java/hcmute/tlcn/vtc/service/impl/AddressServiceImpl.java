package hcmute.tlcn.vtc.service.impl;

import hcmute.tlcn.vtc.dto.AddressDTO;
import hcmute.tlcn.vtc.dto.CustomerDTO;
import hcmute.tlcn.vtc.dto.user.request.AddressRequest;
import hcmute.tlcn.vtc.dto.user.response.AddressResponse;
import hcmute.tlcn.vtc.entity.Address;
import hcmute.tlcn.vtc.entity.Customer;
import hcmute.tlcn.vtc.repository.AddressRepository;
import hcmute.tlcn.vtc.repository.CustomerRepository;
import hcmute.tlcn.vtc.service.IAddressService;
import hcmute.tlcn.vtc.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private final AddressRepository addressRepository;
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final ICustomerService customerService;
    @Autowired
    private final ModelMapper modelMapper;


    @Override
    public AddressResponse addNewAddress(AddressRequest request) {
        request.validate();
        Customer customer = customerService.getCustomerByUsername(request.getUsername());

        AddressDTO addressDTO = request.getAddressDTO();
        Address address = modelMapper.map(addressDTO, Address.class);
        address.setCustomer(customer);
        address.setAtCreate(LocalDateTime.now());
        address.setAtUpdate(LocalDateTime.now());
        address.setStatus(request.getAddressDTO().getStatus());
        addressRepository.save(address);


        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        AddressResponse response = new AddressResponse();
        response.setAddressDTO(addressDTO);
        response.setCustomerDTO(customerDTO);
        response.setStatus("ok");
        response.setCode(200);
        response.setMessage("Thêm địa chỉ mới của khách hàng: " + customer.getFullName() + " thành công.");
        return response;


    }

    @Override
    public AddressResponse updateAddress(AddressRequest request) {
        request.validate();

        Customer customer = customerService.getCustomerByUsername(request.getUsername());
        AddressDTO addressDTO = request.getAddressDTO();

        Address address = new Address();
        address.setProvince(addressDTO.getProvince());
        address.setDistrict(addressDTO.getDistrict());
        address.setFullAddress(addressDTO.getFullAddress());
        address.setFullName(addressDTO.getFullName());
        address.setPhone(addressDTO.getPhone());
        address.setStatus(addressDTO.getStatus());
        address.setAtUpdate(LocalDateTime.now());
        addressRepository.save(address);

        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        AddressResponse response = new AddressResponse();
        response.setAddressDTO(addressDTO);
        response.setCustomerDTO(customerDTO);
        response.setStatus("ok");
        response.setCode(200);
        response.setMessage("Cập nhật địa chỉ của khách hàng: " + customer.getFullName() + " thành công.");
        return response;
    }


}
