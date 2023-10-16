package hcmute.tlcn.vtc.service.vendor.impl;


import hcmute.tlcn.vtc.dto.CustomerDTO;
import hcmute.tlcn.vtc.dto.ShopDTO;
import hcmute.tlcn.vtc.dto.vendor.request.UpdateShopRequest;
import hcmute.tlcn.vtc.dto.vendor.request.RegisterShopRequest;
import hcmute.tlcn.vtc.dto.vendor.response.ShopResponse;
import hcmute.tlcn.vtc.model.entity.Customer;
import hcmute.tlcn.vtc.model.entity.Shop;
import hcmute.tlcn.vtc.model.extra.Role;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.repository.CustomerRepository;
import hcmute.tlcn.vtc.repository.ShopRepository;
import hcmute.tlcn.vtc.service.impl.CustomerServiceImpl;
import hcmute.tlcn.vtc.service.vendor.IShopService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements IShopService {

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public ShopResponse registerShop(RegisterShopRequest request) {

        Customer customer = customerService.getCustomerByUsername(request.getUsername());
        checkEmailAndPhoneAndUsername(request.getEmail(), request.getPhone(), request.getUsername());

        Shop shop = modelMapper.map(request, Shop.class);
        shop.setCustomer(customer);
        shop.setStatus(Status.ACTIVE);
        shop.setAtCreate(LocalDateTime.now());
        shop.setAtUpdate(LocalDateTime.now());

        try {
            Shop save = shopRepository.save(shop);

            ShopDTO shopDTO = modelMapper.map(save, ShopDTO.class);
            customer = addRoleVendor(customer);
            shopDTO.setCustomerDTO(modelMapper.map(customer, CustomerDTO.class));

            ShopResponse shopResponse = new ShopResponse();
            shopResponse.setShopDTO(shopDTO);
            shopResponse.setCode(200);
            shopResponse.setMessage("Đăng ký cửa hàng thành công.");
            shopResponse.setStatus("ok");
            return shopResponse;

        } catch (Exception e) {
            throw new IllegalArgumentException("Đăng ký cửa hàng thất bại!");
        }

    }


    @Override
    public ShopResponse getProfileShop (String username){
        Customer customer = customerService.getCustomerByUsername(username);
        Shop shop = shopRepository.findByCustomer_Username(username);

        if (shop == null) {
            throw new IllegalArgumentException("Tài khoản chưa đăng ký cửa hàng!");
        }

        ShopDTO shopDTO = modelMapper.map(shop, ShopDTO.class);
        shopDTO.setCustomerDTO(modelMapper.map(customer, CustomerDTO.class));

        ShopResponse shopResponse = new ShopResponse();
        shopResponse.setShopDTO(shopDTO);
        shopResponse.setCode(200);
        shopResponse.setMessage("Lấy thông tin cửa hàng thành công.");
        shopResponse.setStatus("ok");

        return shopResponse;

    }


    @Override
    public ShopResponse updateShop(UpdateShopRequest request){

        checkEmailAndPhoneAndUsernameInShop(request.getEmail(), request.getPhone(), request.getUsername());

        Shop shop = shopRepository.findByCustomer_Username(request.getUsername());
        shop.setName(request.getName());
        shop.setAddress(request.getAddress());
        shop.setPhone(request.getPhone());
        shop.setEmail(request.getEmail());
        shop.setOpenTime(request.getOpenTime());
        shop.setCloseTime(request.getCloseTime());
        shop.setDescription(request.getDescription());
        shop.setAtUpdate(LocalDateTime.now());

        try {
            shopRepository.save(shop);

            Customer customer = customerService.getCustomerByUsername(request.getUsername());
            ShopDTO shopDTO = modelMapper.map(shop, ShopDTO.class);
            shopDTO.setCustomerDTO(modelMapper.map(customer, CustomerDTO.class));

            ShopResponse shopResponse = new ShopResponse();
            shopResponse.setShopDTO(shopDTO);
            shopResponse.setCode(200);
            shopResponse.setMessage("Cập nhật cửa hàng thành công.");
            shopResponse.setStatus("ok");
            return shopResponse;
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Cập nhật cửa hàng thất bại!");
        }


    }


    @Override
    public ShopResponse updateStatusShop(String username, Status status){
        Shop shop = shopRepository.findByCustomer_Username(username);
        if (shop == null) {
            throw new IllegalArgumentException("Tài khoản chưa đăng ký cửa hàng!");
        }
        shop.setStatus(status);
        shop.setAtUpdate(LocalDateTime.now());

        try {
            shopRepository.save(shop);

            Customer customer = customerService.getCustomerByUsername(username);
            ShopDTO shopDTO = modelMapper.map(shop, ShopDTO.class);
            shopDTO.setCustomerDTO(modelMapper.map(customer, CustomerDTO.class));

            ShopResponse shopResponse = new ShopResponse();
            shopResponse.setShopDTO(shopDTO);
            shopResponse.setCode(200);
            shopResponse.setMessage("Cập nhật trạng thái cửa hàng thành công.");
            shopResponse.setStatus("ok");
            return shopResponse;
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Cập nhật trạng thái cửa hàng thất bại!");
        }
    }


    private void checkEmailAndPhoneAndUsernameInShop(String email, String phone, String username) {
        Shop shop = shopRepository.findByCustomer_Username(username);

        if (shop == null) {
            throw new IllegalArgumentException("Tài khoản chưa đăng ký cửa hàng!");
        } else {
            // Check if email is used by a shop other than the current shop
            Shop shopByEmail = shopRepository.findByEmail(email);
            if (shopByEmail != null && !shopByEmail.getEmail().equals(shop.getEmail())) {
                throw new IllegalArgumentException("Email đã được sử dụng ở một cửa hàng khác!");
            }

            // Check if phone is used by a shop other than the current shop
            Shop shopByPhone = shopRepository.findByPhone(phone);
            if (shopByPhone != null &&  !shopByPhone.getPhone().equals(shop.getPhone())) {
                throw new IllegalArgumentException("Số điện thoại đã được sử dụng ở một cửa hàng khác!");
            }
        }
    }


    private Customer addRoleVendor(Customer customer) {
        customer.addRole(Role.VENDOR);
        try {
           return customerRepository.save(customer);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cập nhật quyền cho tài khoản thất bại!");
        }

    }


    private void checkEmailAndPhoneAndUsername(String email, String phone, String username) {
        // Check if the email is already used by a shop
        Shop shop = shopRepository.findByEmail(email);
        if (shop != null) {
            throw new IllegalArgumentException("Email đã được sử dụng ở một cửa hàng khác!");
        }

        // Check if the phone is already used by a shop
        shop = shopRepository.findByPhone(phone);
        if (shop != null) {
            throw new IllegalArgumentException("Số điện thoại đã được sử dụng ở một cửa hàng khác!");
        }

        // Check if the username is already used by a customer
        shop = shopRepository.findByCustomer_Username(username);
        if (shop != null) {
            throw new IllegalArgumentException("Tài khoản đã được sử dụng ở một cửa hàng khác!");
        }

    }


}
