package hcmute.tlcn.vtc.service.manager.impl;

import hcmute.tlcn.vtc.repository.CategoryRepository;
import hcmute.tlcn.vtc.service.manager.IManagerCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerCategoryServiceImpl implements IManagerCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


}
