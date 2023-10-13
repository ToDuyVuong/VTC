package hcmute.tlcn.vtc.service.impl;

import hcmute.tlcn.vtc.dto.CategoryDTO;
import hcmute.tlcn.vtc.dto.admin.request.CategoryAdminRequest;
import hcmute.tlcn.vtc.dto.vendor.response.CategoryResponse;
import hcmute.tlcn.vtc.entity.Category;
import hcmute.tlcn.vtc.entity.extra.Status;
import hcmute.tlcn.vtc.repository.CategoryRepository;
import hcmute.tlcn.vtc.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse addNewCategory (CategoryAdminRequest request){

        Category category = modelMapper.map(request, Category.class);
        category.setAdminOnly(true);
        category.setStatus(Status.ACTIVE);
        category.setAtCreate(LocalDateTime.now());
        category.setAtUpdate(LocalDateTime.now());



        categoryRepository.save(category);

        CategoryResponse response = new CategoryResponse();
        response.setCategoryDTO(modelMapper.map(category, CategoryDTO.class));
        response.setCode(200);
        response.setMessage("Thêm danh mục thành công từ admin thành công!");
        response.setStatus("ok");
        return response;
    }

}
