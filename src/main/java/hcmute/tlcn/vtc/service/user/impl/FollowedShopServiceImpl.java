package hcmute.tlcn.vtc.service.user.impl;

import hcmute.tlcn.vtc.model.dto.FollowedShopDTO;
import hcmute.tlcn.vtc.model.data.user.response.FollowedShopResponse;
import hcmute.tlcn.vtc.model.data.user.response.ListFollowedShopResponse;
import hcmute.tlcn.vtc.model.entity.*;
import hcmute.tlcn.vtc.repository.FollowedShopRepository;
import hcmute.tlcn.vtc.repository.ShopRepository;
import hcmute.tlcn.vtc.service.user.ICustomerService;
import hcmute.tlcn.vtc.service.user.IFollowedShopService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowedShopServiceImpl implements IFollowedShopService {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private FollowedShopRepository followedShopRepository;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public FollowedShopResponse addNewFollowedShop(Long shopId, String username) {

        boolean isExist = followedShopRepository.existsByCustomerUsernameAndShopShopId(username, shopId);
        if (isExist) {
            throw new IllegalArgumentException("Bạn đã theo dõi cửa hàng này!");
        }

        Customer customer = customerService.getCustomerByUsername(username);
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("Cửa hàng không tồn tại!"));

        FollowedShop followedShop = new FollowedShop();
        followedShop.setCustomer(customer);
        followedShop.setShop(shop);
        followedShop.setCreateAt(LocalDateTime.now());

        try {
            followedShopRepository.save(followedShop);

            FollowedShopResponse response = new FollowedShopResponse();
            response.setFollowedShopDTO(modelMapper.map(followedShop, FollowedShopDTO.class));
            response.setMessage("Theo dõi cửa hàng thành công!");
            response.setStatus("success");
            response.setCode(200);

            return response;
        } catch (Exception e) {
            throw new IllegalArgumentException("Lỗi theo dõi cửa hàng!");
        }
    }


    @Override
    public ListFollowedShopResponse getListFollowedShopByUsername(String username) {
        List<FollowedShop> followedShops = followedShopRepository.findAllByCustomerUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Không có cửa hàng nào được theo dõi!"));

        ListFollowedShopResponse response = new ListFollowedShopResponse();
        response.setFollowedShopDTOs(FollowedShopDTO.convertToListDTO(followedShops));
        response.setCount(followedShops.size());
        response.setMessage("Lấy danh sách cửa hàng theo dõi thành công.");
        response.setStatus("ok");
        response.setCode(200);
        return response;
    }


    @Override
    public FollowedShopResponse deleteFollowedShop(Long followedShopId, String username) {
        FollowedShop followedShop = followedShopRepository.findById(followedShopId)
                .orElseThrow(() -> new IllegalArgumentException("Theo dõi cửa hàng không tồn tại!"));

        if (!followedShop.getCustomer().getUsername().equals(username)) {
            throw new IllegalArgumentException("Bạn không có quyền xóa theo dõi cửa hàng này!");
        }

        try {
            followedShopRepository.delete(followedShop);

            FollowedShopResponse response = new FollowedShopResponse();
            response.setMessage("Xóa theo dõi cửa hàng thành công!");
            response.setStatus("success");
            response.setCode(200);
            return response;
        } catch (Exception e) {
            throw new IllegalArgumentException("Lỗi xóa theo dõi cửa hàng!");
        }
    }


}
