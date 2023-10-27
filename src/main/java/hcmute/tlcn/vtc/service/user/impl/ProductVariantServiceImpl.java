package hcmute.tlcn.vtc.service.user.impl;

import hcmute.tlcn.vtc.repository.ProductVariantRepository;
import hcmute.tlcn.vtc.service.user.IProductVariantService;
import hcmute.tlcn.vtc.service.vendor.IAttributeShopService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductVariantServiceImpl implements IProductVariantService {

    @Autowired
    private IAttributeShopService attributeService;
    @Autowired
    private ProductVariantRepository productVariantRepository;
    @Autowired
    ModelMapper modelMapper;







}
