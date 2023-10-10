package hcmute.tlcn.vtc.service.impl;


import hcmute.tlcn.vtc.dto.CustomerDTO;
import hcmute.tlcn.vtc.dto.ShopDTO;
import hcmute.tlcn.vtc.dto.vendor.request.RegisterShopRequest;
import hcmute.tlcn.vtc.dto.vendor.response.ShopResponse;
import hcmute.tlcn.vtc.entity.Customer;
import hcmute.tlcn.vtc.entity.Shop;
import hcmute.tlcn.vtc.entity.extra.Status;
import hcmute.tlcn.vtc.repository.ShopRepository;
import hcmute.tlcn.vtc.service.IShopService;
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
            shopDTO.setCustomerDTO(modelMapper.map(customer, CustomerDTO.class));

            ShopResponse shopResponse = new ShopResponse();
            shopResponse.setShopDTO(shopDTO);
            shopResponse.setCode(200);
            shopResponse.setMessage("Đăng ký cửa hàng thành công!");
            shopResponse.setStatus("ok");
            return shopResponse;

        } catch (Exception e) {
            throw new IllegalArgumentException("Đăng ký cửa hàng thất bại!");
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
