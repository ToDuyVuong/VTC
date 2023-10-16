package hcmute.tlcn.vtc.service.vendor.impl;

import hcmute.tlcn.vtc.model.dto.CategoryDTO;
import hcmute.tlcn.vtc.model.dto.vendor.request.CategoryShopRequest;
import hcmute.tlcn.vtc.model.dto.vendor.response.AllCategoryShopResponse;
import hcmute.tlcn.vtc.model.dto.vendor.response.CategoryShopResponse;
import hcmute.tlcn.vtc.model.entity.Category;
import hcmute.tlcn.vtc.model.entity.Shop;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.repository.CategoryRepository;
import hcmute.tlcn.vtc.repository.CustomerRepository;
import hcmute.tlcn.vtc.repository.ShopRepository;
import hcmute.tlcn.vtc.service.user.impl.CustomerServiceImpl;
import hcmute.tlcn.vtc.service.vendor.ICategoryShopService;
import hcmute.tlcn.vtc.util.exception.DuplicateEntryException;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import hcmute.tlcn.vtc.util.exception.SaveFailedException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryShopServiceImpl implements ICategoryShopService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public CategoryShopResponse addNewCategoryShop(CategoryShopRequest request) {

        Shop shop = shopRepository.findById(request.getShopId())
                .orElseThrow(() -> new NotFoundException("Cửa hàng không tồn tại!"));
        Optional<Category> existingCategory = categoryRepository.findByNameAndShopShopId(request.getName(), shop.getShopId());
        if (existingCategory.isPresent()) {
            throw new DuplicateEntryException("Tên danh mục đã tồn tại trong cửa hàng!");
        }
        Category parent = categoryRepository.findByCategoryIdAndAdminOnly(request.getParentId(), true)
                .orElseThrow(() -> new NotFoundException("Danh mục cha không tồn tại!"));

        Category category = modelMapper.map(request, Category.class);
        category.setAdminOnly(false);
        category.setStatus(Status.ACTIVE);
        category.setShop(shop);
        category.setParent(parent);
        category.setAtCreate(LocalDateTime.now());
        category.setAtUpdate(LocalDateTime.now());

        try {
            Category categorySave = categoryRepository.save(category);

            CategoryDTO categoryDTO = modelMapper.map(categorySave, CategoryDTO.class);
            categoryDTO.setShopId(shop.getShopId());
            categoryDTO.setParentId(parent.getCategoryId());

            CategoryShopResponse response = new CategoryShopResponse();
            response.setCategoryDTO(categoryDTO);
            response.setCode(200);
            response.setMessage("Thêm danh mục vào cửa hàng " + shop.getName() + " thành công!");
            response.setStatus("ok");

            return response;
        } catch (Exception e) {
            throw new SaveFailedException("Thêm danh mục của cửa hàng thất bại!");
        }
    }


    @Override
    public AllCategoryShopResponse getAllCategoryByShopId(Long shopId) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new NotFoundException("Cửa hàng không tồn tại!"));

        AllCategoryShopResponse response = new AllCategoryShopResponse();
        response.setCategoryDTOS(CategoryDTO.convertToListDTO(categoryRepository.findAllByShopShopId(shopId)));
        response.setCode(200);
        response.setMessage("Lấy danh sách danh mục của cửa hàng " + shop.getName() + " thành công!");
        response.setStatus("ok");

        return response;
    }


    @Override
    public CategoryShopResponse getCategoryById(Long categoryId, Long shopId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Danh mục không tồn tại!"));

        if (category.getShop() == null || !category.getShop().getShopId().equals(shopId) ) {
            throw new NotFoundException("Danh mục không tồn tại trong cửa hàng!");
        }

        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        categoryDTO.setShopId(category.getShop().getShopId());
        categoryDTO.setParentId(category.getParent().getCategoryId());

        CategoryShopResponse response = new CategoryShopResponse();
        response.setCategoryDTO(categoryDTO);
        response.setCode(200);
        response.setMessage("Lấy danh mục thành công.");
        response.setStatus("ok");

        return response;
    }


}


