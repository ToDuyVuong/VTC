package hcmute.tlcn.vtc.service.impl;

import hcmute.tlcn.vtc.dto.BrandDTO;
import hcmute.tlcn.vtc.dto.admin.request.BrandAdminRequest;
import hcmute.tlcn.vtc.dto.admin.response.AllBrandAdminResponse;
import hcmute.tlcn.vtc.dto.admin.response.BrandAdminResponse;
import hcmute.tlcn.vtc.entity.Brand;
import hcmute.tlcn.vtc.entity.Customer;
import hcmute.tlcn.vtc.entity.extra.Role;
import hcmute.tlcn.vtc.entity.extra.Status;
import hcmute.tlcn.vtc.repository.BrandRepository;
import hcmute.tlcn.vtc.service.IBrandAdminService;
import hcmute.tlcn.vtc.service.ICustomerService;
import hcmute.tlcn.vtc.util.exception.UnauthorizedAccessException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandAdminServiceImpl implements IBrandAdminService {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ICustomerService customerService;


    @Override
    public BrandAdminResponse addNewBrand(BrandAdminRequest request) {

        Customer customer = customerService.getCustomerByUsername(request.getUsername());
        if (!customer.getRoles().contains(Role.ADMIN)) {
            throw new UnauthorizedAccessException("Bạn không có quyền thêm thương hiệu!");
        }

        Brand brand = brandRepository.findByName(request.getName());
        if (brand != null) {
            throw new IllegalArgumentException("Tên thương hiệu đã tồn tại!");
        }

        brand = modelMapper.map(request, Brand.class);
        brand.setAdminOnly(true);
        brand.setAtCreate(LocalDateTime.now());
        brand.setAtUpdate(LocalDateTime.now());
        brand.setStatus(Status.ACTIVE);
        brand.setCustomer(customer);

        try {
            Brand saveBrand = brandRepository.save(brand);

            BrandDTO brandDTO = modelMapper.map(saveBrand, BrandDTO.class);
            BrandAdminResponse response = new BrandAdminResponse();
            response.setBrandDTO(brandDTO);
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Thêm thương hiệu thành công!");

            return response;
        } catch (Exception e) {
            throw new IllegalArgumentException("Thêm thương hiệu thất bại!");
        }
    }


    @Override
    public BrandAdminResponse getBrandById(Long brandId) {

        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy thương hiệu!"));

        BrandDTO brandDTO = modelMapper.map(brand, BrandDTO.class);

        BrandAdminResponse response = new BrandAdminResponse();
        response.setBrandDTO(brandDTO);
        response.setCode(200);
        response.setStatus("success");
        response.setMessage("Lấy thông tin thương hiệu thành công.");

        return response;
    }


    @Override
    public AllBrandAdminResponse getAllBrandAdmin() {

        List<Brand> brands = brandRepository.findAllByAdminOnly(true);
        if (brands == null || brands.isEmpty()) {
            throw new IllegalArgumentException("Không có thương hiệu nào!");
        }

        List<BrandDTO> brandDTOS = BrandDTO.convertToListDTO(brands);

        AllBrandAdminResponse response = new AllBrandAdminResponse();
        response.setBrandDTOS(brandDTOS);
        response.setCode(200);
        response.setStatus("success");
        response.setMessage("Lấy danh sách thương hiệu thành công.");
        return response;
    }


    @Override
    public BrandAdminResponse updateBrandAdmin(BrandAdminRequest request) {

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy thương hiệu!"));
        if (!brand.getCustomer().getUsername().equals(request.getUsername())) {
            throw new UnauthorizedAccessException("Bạn không có quyền sửa thương hiệu này!");
        }

        if (!request.getName().equals(brand.getName())) {
            Brand brandCheck = brandRepository.findByName(request.getName());
            if (brandCheck != null) {
                throw new IllegalArgumentException("Tên thương hiệu đã tồn tại!");
            }
        }

        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        brand.setInformation(request.getInformation());
        brand.setOrigin(request.getOrigin());
        brand.setImage(request.getImage());
        brand.setAtUpdate(LocalDateTime.now());

        try {
            brandRepository.save(brand);

            BrandDTO brandDTO = modelMapper.map(brand, BrandDTO.class);
            BrandAdminResponse response = new BrandAdminResponse();
            response.setBrandDTO(brandDTO);
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Cập nhật thương hiệu thành công.");

            return response;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cập nhật thương hiệu thất bại!");
        }
    }


    @Override
    public BrandAdminResponse updateStatusBrandAdmin(Long brandId, String username, Status status) {

            Brand brand = brandRepository.findById(brandId)
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy thương hiệu!"));
            if (!brand.getCustomer().getUsername().equals(username)) {
                throw new UnauthorizedAccessException("Bạn không có quyền sửa thương hiệu này!");
            }
            if (brand.getStatus() == Status.DELETED){
                throw new IllegalArgumentException("Thương hiệu đã bị xóa!");
            }

            brand.setStatus(status);
            brand.setAtUpdate(LocalDateTime.now());

            try {
                brandRepository.save(brand);

                BrandDTO brandDTO = modelMapper.map(brand, BrandDTO.class);
                BrandAdminResponse response = new BrandAdminResponse();
                response.setBrandDTO(brandDTO);
                response.setCode(200);
                response.setStatus("success");
                response.setMessage("Cập nhật trạng thái thương hiệu thành công.");

                return response;
            } catch (Exception e) {
                throw new IllegalArgumentException("Cập nhật trạng thái thương hiệu thất bại!");
            }
    }

}
