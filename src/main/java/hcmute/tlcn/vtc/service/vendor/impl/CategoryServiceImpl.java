package hcmute.tlcn.vtc.service.vendor.impl;

import hcmute.tlcn.vtc.repository.CategoryRepository;
import hcmute.tlcn.vtc.service.vendor.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


}


