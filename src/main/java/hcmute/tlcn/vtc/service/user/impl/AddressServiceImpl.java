package hcmute.tlcn.vtc.service.user.impl;

import hcmute.tlcn.vtc.model.dto.AddressDTO;
import hcmute.tlcn.vtc.model.dto.CustomerDTO;
import hcmute.tlcn.vtc.model.data.user.request.AddressRequest;
import hcmute.tlcn.vtc.model.data.user.request.AddressStatusRequest;
import hcmute.tlcn.vtc.model.data.user.response.AddressResponse;
import hcmute.tlcn.vtc.model.data.user.response.ListAddressResponse;
import hcmute.tlcn.vtc.model.entity.vtc.Address;
import hcmute.tlcn.vtc.model.entity.vtc.Customer;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.repository.AddressRepository;
import hcmute.tlcn.vtc.service.user.IAddressService;
import hcmute.tlcn.vtc.service.user.ICustomerService;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private final AddressRepository addressRepository;
    @Autowired
    private final ICustomerService customerService;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public AddressResponse addNewAddress(AddressRequest request) {
        // request.validate();
        Customer customer = customerService.getCustomerByUsername(request.getUsername());

        AddressDTO addressDTO = AddressRequest.convertRequestToDTO(request);
        Address address = AddressDTO.convertDTOToEntity(addressDTO);
        address.setCustomer(customer);
        address.setCreateAt(LocalDateTime.now());
        address.setUpdateAt(LocalDateTime.now());
        address.setStatus(Status.ACTIVE);

        try {
            Address addressSave = addressRepository.save(address);

            updateStatusInActiveWithAddress(customer, addressSave.getAddressId(), addressSave.getUpdateAt());


            AddressDTO addressDTOSave = AddressDTO.convertEntityToDTO(addressSave);
            CustomerDTO customerDTO = CustomerDTO.convertEntityToDTO(customer);

            AddressResponse response = new AddressResponse();
            response.setAddressDTO(addressDTOSave);
            response.setCustomerDTO(customerDTO);
            response.setStatus("success");
            response.setCode(200);
            response.setMessage("Thêm địa chỉ mới của khách hàng: " + customer.getFullName() + " thành công.");

            return response;
        } catch (Exception e) {
            throw new IllegalArgumentException("Thêm địa chỉ mới thất bại.");
        }

    }

    @Override
    public AddressResponse getAddressById(String addressId, String username) {
        if (addressId == null || addressId.isEmpty()) {
            throw new IllegalArgumentException("Mã địa chỉ không được để trống.");
        }

        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Tài khoản không được để trống.");
        }

        Long id = Long.valueOf(addressId);
        Address address = checkAddress(id, username);

        AddressDTO addressDTO = AddressDTO.convertEntityToDTO(address);
        CustomerDTO customerDTO = CustomerDTO.convertEntityToDTO(address.getCustomer());

        AddressResponse response = new AddressResponse();
        response.setAddressDTO(addressDTO);
        response.setCustomerDTO(customerDTO);
        response.setStatus("ok");
        response.setCode(200);
        response.setMessage("Lấy thông tin địa chỉ thành công.");

        return response;
    }

    @Override
    @Transactional
    public AddressResponse updateAddress(AddressRequest request) {
        request.validate();

        Customer customer = customerService.getCustomerByUsername(request.getUsername());
        AddressDTO addressDTO = AddressRequest.convertUpdateRequestToDTO(request);

        Address address = checkAddress(addressDTO.getAddressId(), request.getUsername());
        address.setProvince(addressDTO.getProvince());
        address.setDistrict(addressDTO.getDistrict());
        address.setWard(addressDTO.getWard());
        address.setFullAddress(addressDTO.getFullAddress());
        address.setFullName(addressDTO.getFullName());
        address.setPhone(addressDTO.getPhone());
        address.setStatus(addressDTO.getStatus());
        address.setUpdateAt(LocalDateTime.now());

        try {
            addressRepository.save(address);

            CustomerDTO customerDTO = CustomerDTO.convertEntityToDTO(customer);
            AddressResponse response = new AddressResponse();
            response.setAddressDTO(addressDTO);
            response.setCustomerDTO(customerDTO);
            response.setStatus("success");
            response.setCode(200);
            response.setMessage("Cập nhật địa chỉ của khách hàng: " + customer.getFullName() + " thành công.");
            return response;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cập nhật địa chỉ mới thất bại.");
        }

    }

    @Override
    @Transactional
    public AddressResponse updateStatusAddress(AddressStatusRequest request) {
        request.validate();

        Customer customer = customerService.getCustomerByUsername(request.getUsername());
        Long addressId = Long.valueOf(request.getAddressId());
        Address address = checkAddress(addressId, request.getUsername());

        if (address.getCustomer().getCustomerId().equals(customer.getCustomerId())) {
            address.setUpdateAt(LocalDateTime.now());
            address.setStatus(request.getStatus());
            checkStatus(request, address, customer, addressId);

            try {
                addressRepository.save(address);

                String message = setMessageUpdateStatus(request.getStatus(), customer.getFullName());
                AddressDTO addressDTO = AddressDTO.convertEntityToDTO(address);
                CustomerDTO customerDTO = CustomerDTO.convertEntityToDTO(customer);
                AddressResponse response = new AddressResponse();
                response.setCustomerDTO(customerDTO);
                response.setAddressDTO(addressDTO);
                response.setStatus("success");
                response.setCode(200);
                response.setMessage(message);

                return response;
            } catch (Exception e) {
                throw new IllegalArgumentException("Cập nhật trạng thái địa chỉ mới thất bại.");
            }

        } else {
            throw new IllegalArgumentException("Địa chỉ không thuộc về khách hàng này.");
        }
    }

    @Override
    public ListAddressResponse getAllAddress(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Tài khoản không được để trống.");
        }

        Customer customer = customerService.getCustomerByUsername(username);
        List<Address> addresses = addressRepository.findAllByCustomerAndStatusNot(customer, Status.DELETED)
                .orElseThrow(() -> new NotFoundException("Khách hàng chưa có địa chỉ nào."));
        CustomerDTO customerDTO = CustomerDTO.convertEntityToDTO(customer);

        addresses.sort(Comparator.comparing(Address::getStatus,
                Comparator.comparingInt(s -> s == Status.ACTIVE ? 0 : (s == Status.INACTIVE ? 1 : 2))));

        List<AddressDTO> addressDTOs = AddressDTO.convertToListDTO(addresses);

        ListAddressResponse response = new ListAddressResponse();
        response.setAddressDTOs(addressDTOs);
        response.setCustomerDTO(customerDTO);
        response.setStatus("ok");
        response.setCode(200);
        response.setMessage("Lấy danh sách địa chỉ của khách hàng: " + customer.getFullName() + " thành công.");

        return response;
    }


    @Override
    public Address getAddressActiveByUsername(String username) {
        Address address = addressRepository.findFirstByCustomerUsernameAndStatus(username, Status.ACTIVE)
                .orElseThrow(() -> new NotFoundException("Khách hàng chưa có địa chỉ nào."));
        if (address == null) {
            throw new NotFoundException("Khách hàng chưa có địa chỉ nào sẳn sàng.");
        }

        return address;
    }


    @Override
    public Address getAddressByIdAndUsername(Long addressId, String username) {
        Address address = addressRepository.findByAddressIdAndCustomerUsername(addressId, username)
                .orElseThrow(() -> new NotFoundException("Mã địa chỉ không tồn tại."));
        if (address == null) {
            throw new NotFoundException("Khách hàng chưa có địa chỉ nào sẳn sàng.");
        }
        if (address.getStatus().equals(Status.DELETED)) {
            throw new NotFoundException("Địa chỉ đã bị xóa.");
        }

        return address;
    }


    private Address checkAddress(Long addressId, String username) {

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException("Địa chỉ không tồn tại."));

        if (!address.getCustomer().getUsername().equals(username)) {
            throw new IllegalArgumentException("Địa chỉ không thuộc về khách hàng này.");
        }

        if (address.getStatus().equals(Status.DELETED)) {
            throw new IllegalArgumentException("Địa chỉ đã bị xóa.");
        }


        return address;
    }


    @Transactional
    public void checkStatus(AddressStatusRequest request, Address address, Customer customer, Long addressId) {
        if (request.getStatus().equals(Status.DELETED)) {
            address.setStatus(Status.DELETED);
        } else if (request.getStatus().equals(Status.ACTIVE)) {
            updateStatusInActiveWithAddress(customer, addressId, address.getUpdateAt());
            address.setStatus(Status.ACTIVE);
        } else if (request.getStatus().equals(Status.INACTIVE)) {
            address.setStatus(Status.INACTIVE);
        } else {
            throw new IllegalArgumentException("Trạng thái không hợp lệ.");
        }
    }


    private String setMessageUpdateStatus(Status status, String fullName) {
        if (status.equals(Status.DELETED)) {
            return "Xóa địa chỉ của khách hàng: " + fullName + " thành công.";
        }
        return "Cập nhật trạng thái địa chỉ của khách hàng: " + fullName + " thành công.";

    }

    @Transactional
    public void updateStatusInActiveWithAddress(Customer customer, Long addressId, LocalDateTime updateAt) {

        List<Address> addresses = addressRepository.findAllByCustomerAndStatusAndAddressIdNot(customer, Status.ACTIVE, addressId)
                .orElseThrow(() -> new NotFoundException("Khách hàng chưa có địa chỉ nào."));

        if (addresses != null && addresses.size() > 0) {
            for (Address address : addresses) {
                address.setStatus(Status.INACTIVE);
                address.setUpdateAt(updateAt);
                try {
                    addressRepository.save(address);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Cập nhật trạng thái địa chỉ mới thất bại.");
                }
            }
        }

    }

}
