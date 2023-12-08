package hcmute.tlcn.vtc.service.manager.impl;

import hcmute.tlcn.vtc.model.data.manager.response.ListShopManagerResponse;
import hcmute.tlcn.vtc.model.data.manager.response.ManagerShopResponse;
import hcmute.tlcn.vtc.model.dto.ShopDTO;
import hcmute.tlcn.vtc.model.entity.vtc.Shop;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.repository.ShopRepository;
import hcmute.tlcn.vtc.service.manager.IManagerShopService;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ManagerShopServiceImpl implements IManagerShopService {

    @Autowired
    private ShopRepository shopRepository;


    @Override
    public ManagerShopResponse getShopById(Long id) {
        Shop shop = shopRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Không tìm thấy cửa hàng!"));

        ManagerShopResponse response = new ManagerShopResponse();
        response.setShopDTO(ShopDTO.convertEntityToDTO(shop));
        response.setMessage("Lấy thông tin cửa hàng thành công!");
        response.setCode(200);
        response.setStatus("OK");
        return response;
    }


    @Override
    public ListShopManagerResponse getListShop(int size, int page, Status status) {
        int totalShop = shopRepository.countAllByStatus(status);
        int totalPage = (int) Math.ceil((double) totalShop / size);

        Page<Shop> shops = shopRepository.findAllByStatus(status, PageRequest.of(page - 1, size)).
                orElseThrow(() -> new IllegalArgumentException("Không tìm thấy danh sách cửa hàng"));

        return listShopManagerResponse(shops.getContent(),
                size, page, totalPage, "Lấy danh sách cửa hàng theo trạng thái thành công!");
    }


    @Override
    public ListShopManagerResponse getListShopByName(int size, int page, String name, Status status) {
        int totalShop = shopRepository.countByNameContainsAndStatus(name, status);
        int totalPage = (int) Math.ceil((double) totalShop / size);

        Page<Shop> shops = shopRepository.findAllByNameContainsAndStatusOrderByName(name, status, PageRequest.of(page - 1, size)).
                orElseThrow(() -> new IllegalArgumentException("Không tìm thấy danh sách cửa hàng"));



        return listShopManagerResponse(shops.getContent(),
                size, page, totalPage, "Lấy danh sách cửa hàng theo tên thành công!");
    }

    public ListShopManagerResponse listShopManagerResponse(List<Shop> shops, int size, int page, int totalPage, String message) {
        ListShopManagerResponse response = new ListShopManagerResponse();
        response.setShopDTOs(ShopDTO.convertEntitiesToDTOs(shops));
        response.setSize(size);
        response.setPage(page);
        response.setTotalPage(totalPage);
        response.setCount(shops.size());
        response.setMessage(message);
        response.setCode(200);
        response.setStatus("OK");

        return response;
    }


    @Override
    public void checkRequestPageParams(int page, int size) {
        if (page < 0) {
            throw new NotFoundException("Trang không được nhỏ hơn 0!");
        }
        if (size < 0) {
            throw new NotFoundException("Kích thước trang không được nhỏ hơn 0!");
        }
        if (size > 500) {
            throw new NotFoundException("Kích thước trang không được lớn hơn 200!");
        }
    }


}
