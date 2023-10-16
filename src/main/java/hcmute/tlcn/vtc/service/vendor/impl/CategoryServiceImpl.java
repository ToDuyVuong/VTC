package hcmute.tlcn.vtc.service.vendor.impl;

import hcmute.tlcn.vtc.model.dto.vendor.request.CategoryShopRequest;
import hcmute.tlcn.vtc.model.dto.vendor.response.CategoryShopResponse;
import hcmute.tlcn.vtc.repository.CategoryRepository;
import hcmute.tlcn.vtc.repository.CustomerRepository;
import hcmute.tlcn.vtc.repository.ShopRepository;
import hcmute.tlcn.vtc.service.user.impl.CustomerServiceImpl;
import hcmute.tlcn.vtc.service.vendor.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

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


    public CategoryShopResponse addNewCategory(CategoryShopRequest request) {
        return null;
    }











}


