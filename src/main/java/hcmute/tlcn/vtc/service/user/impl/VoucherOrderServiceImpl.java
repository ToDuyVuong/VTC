package hcmute.tlcn.vtc.service.user.impl;

import hcmute.tlcn.vtc.repository.VoucherOrderRepository;
import hcmute.tlcn.vtc.service.user.IVoucherOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoucherOrderServiceImpl implements IVoucherOrderService {

    @Autowired
    private final VoucherOrderRepository voucherOrderRepository;










}
