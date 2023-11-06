package hcmute.tlcn.vtc.service.user.impl;

import hcmute.tlcn.vtc.repository.OrderItemRepository;
import hcmute.tlcn.vtc.service.user.IOrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements IOrderItemService {

    @Autowired
    private final OrderItemRepository orderItemRepository;










}
