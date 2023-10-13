package hcmute.tlcn.vtc.service.impl;

import hcmute.tlcn.vtc.dto.CategoryDTO;
import hcmute.tlcn.vtc.dto.admin.CategoryAdminDTO;
import hcmute.tlcn.vtc.dto.admin.request.CategoryAdminRequest;
import hcmute.tlcn.vtc.dto.admin.response.AllCategoryAdminResponse;
import hcmute.tlcn.vtc.dto.admin.response.CategoryAdminResponse;
import hcmute.tlcn.vtc.dto.vendor.response.CategoryResponse;
import hcmute.tlcn.vtc.entity.Category;
import hcmute.tlcn.vtc.entity.extra.Status;
import hcmute.tlcn.vtc.repository.CategoryRepository;
import hcmute.tlcn.vtc.service.ICategoryAdminService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryAdminAdminAdminServiceImpl implements ICategoryAdminService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

//    @Override
//    public CategoryResponse addNewCategory (CategoryAdminRequest request){
//
//        Category category = modelMapper.map(request, Category.class);
//        category.setAdminOnly(true);
//        category.setStatus(Status.ACTIVE);
//        category.setAtCreate(LocalDateTime.now());
//        category.setAtUpdate(LocalDateTime.now());
//
//
//
//        categoryRepository.save(category);
//
//        CategoryResponse response = new CategoryResponse();
//        response.setCategoryDTO(modelMapper.map(category, CategoryDTO.class));
//        response.setCode(200);
//        response.setMessage("Thêm danh mục thành công từ admin thành công!");
//        response.setStatus("ok");
//        return response;
//    }

    @Override
    public CategoryAdminResponse addNewCategory(CategoryAdminRequest request) {

        Category category = modelMapper.map(request, Category.class);
        category.setAdminOnly(true);
        category.setStatus(Status.ACTIVE);
        category.setAtCreate(LocalDateTime.now());
        category.setAtUpdate(LocalDateTime.now());


        try {
            Category categorySave = categoryRepository.save(category);
            System.out.println(categorySave);

            CategoryAdminDTO categoryAdminDTO = modelMapper.map(categorySave, CategoryAdminDTO.class);
            System.out.println(categoryAdminDTO);


            CategoryAdminResponse response = new CategoryAdminResponse();
            response.setCategoryAdminDTO(categoryAdminDTO);
            response.setCode(200);
            response.setMessage("Thêm danh mục thành công từ admin thành công!");
            response.setStatus("ok");

            return response;
        } catch (Exception e) {
            CategoryAdminResponse response = new CategoryAdminResponse();
            response.setCode(500);
            response.setMessage("Thêm danh mục từ admin thất bại!");
            response.setStatus("error");
            return response;
        }
    }




    @Override
    public AllCategoryAdminResponse getParentCategory() {
        List<Category> categories = categoryRepository.findAllByAdminOnly(true);
        if (categories.isEmpty()) {
            AllCategoryAdminResponse response = new AllCategoryAdminResponse();
            response.setCode(404);
            response.setMessage("Không tìm thấy danh mục!");
            response.setStatus("error");
            return response;
        }

        List<CategoryAdminDTO> categoryAdminDTOS = CategoryAdminDTO.convertToListDTO(categories);

        AllCategoryAdminResponse response = new AllCategoryAdminResponse();
        response.setCategoryAdminDTOS(categoryAdminDTOS);
        response.setCode(200);
        response.setMessage("Lấy danh mục thành công!");
        response.setStatus("ok");

        return response;

    }

}
