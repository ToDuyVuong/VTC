package hcmute.tlcn.vtc.service.impl;

import hcmute.tlcn.vtc.dto.BrandDTO;
import hcmute.tlcn.vtc.dto.admin.request.BrandAdminRequest;
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

@Service
@RequiredArgsConstructor
public class BrandAdminAdminServiceImpl implements IBrandAdminService {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ICustomerService customerService;


    @Override
    public BrandAdminResponse addNewBrand(BrandAdminRequest request) {

        Customer customer = customerService.getCustomerByUsername(request.getUsername());

        System.out.println("customer: " + customer);

        if(!customer.getRoles().contains(Role.ADMIN)){
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
        Brand saveBrand =    brandRepository.save(brand);

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


}
