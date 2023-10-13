package hcmute.tlcn.vtc.service.impl;

import hcmute.tlcn.vtc.dto.admin.CategoryAdminDTO;
import hcmute.tlcn.vtc.dto.admin.request.CategoryAdminRequest;
import hcmute.tlcn.vtc.dto.admin.response.AllCategoryAdminResponse;
import hcmute.tlcn.vtc.dto.admin.response.CategoryAdminResponse;
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
    public CategoryAdminResponse getCategoryParent(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy danh mục!"));

        CategoryAdminDTO categoryAdminDTO = modelMapper.map(category, CategoryAdminDTO.class);

        CategoryAdminResponse response = new CategoryAdminResponse();
        response.setCategoryAdminDTO(categoryAdminDTO);
        response.setCode(200);
        response.setMessage("Lấy chi tiết danh mục thành công.");
        response.setStatus("ok");

        return response;
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


    @Override
    public CategoryAdminResponse updateCategoryParent(CategoryAdminRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy danh mục!"));

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setImage(request.getImage());
        category.setAtUpdate(LocalDateTime.now());

        try {
            Category categorySave = categoryRepository.save(category);
            CategoryAdminDTO categoryAdminDTO = modelMapper.map(categorySave, CategoryAdminDTO.class);

            CategoryAdminResponse response = new CategoryAdminResponse();
            response.setCategoryAdminDTO(categoryAdminDTO);
            response.setCode(200);
            response.setMessage("Cập nhật danh mục thành công!");
            response.setStatus("ok");

            return response;
        } catch (Exception e) {
            CategoryAdminResponse response = new CategoryAdminResponse();
            response.setCode(500);
            response.setMessage("Cập nhật danh mục thất bại!");
            response.setStatus("error");

            return response;
        }
    }


    @Override
    public CategoryAdminResponse updateStatusCategoryParent(Long categoryId, Status status) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy danh mục!"));

        category.setStatus(status);
        category.setAtUpdate(LocalDateTime.now());

        if (status == Status.DELETED) {
            List<Category> categories = categoryRepository.findAllByParent(category);
            if (!categories.isEmpty()) {
                for (Category category1 : categories) {
                    category1.setStatus(Status.DELETED);
                    category1.setAtUpdate(LocalDateTime.now());
                    try {
                        categoryRepository.save(category1);
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Cập nhật danh mục con thất bại!");
                    }
                }
            }
        }

        try {
            Category categorySave = categoryRepository.save(category);
            CategoryAdminDTO categoryAdminDTO = modelMapper.map(categorySave, CategoryAdminDTO.class);

            CategoryAdminResponse response = new CategoryAdminResponse();
            response.setCategoryAdminDTO(categoryAdminDTO);
            response.setCode(200);
            response.setMessage("Cập nhật danh mục thành công!");
            response.setStatus("ok");

            return response;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cập nhật danh mục thất bại!");
        }
    }

//    @Override
//    public CategoryAdminResponse updateStatusCategoryParent(Long categoryId, Status status) {
//        Category category = categoryRepository.findById(categoryId)
//                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy danh mục!"));
//
//        category.setStatus(status);
//        category.setAtUpdate(LocalDateTime.now());
//
//
//        if (status == Status.DELETED) {
//            List<Category> categories = categoryRepository.findAllByParent(category);
//            if (!categories.isEmpty()) {
//                for (Category category1 : categories) {
//                    category1.setStatus(Status.DELETED);
//                    category1.setAtUpdate(LocalDateTime.now());
//                    try {
//                        categoryRepository.save(category1);
//                    } catch (Exception e) {
//                        throw new IllegalArgumentException("Cập nhật danh mục con thất bại!");
//                    }
//                }
//                try {
//                    categoryRepository.save(category);
//                } catch (Exception e) {
//                    throw new IllegalArgumentException("Cập nhật danh mục cha thất bại!");
//                }
//
//            }
//        }
//
//        try {
//            Category categorySave = categoryRepository.save(category);
//            CategoryAdminDTO categoryAdminDTO = modelMapper.map(categorySave, CategoryAdminDTO.class);
//
//            CategoryAdminResponse response = new CategoryAdminResponse();
//            response.setCategoryAdminDTO(categoryAdminDTO);
//            response.setCode(200);
//            response.setMessage("Cập nhật danh mục thành công!");
//            response.setStatus("ok");
//
//            return response;
//        } catch (Exception e) {
//           throw new IllegalArgumentException("Cập nhật danh mục thất bại!");
//        }
//    }

}
