package hcmute.tlcn.vtc.service.guest.impl;

import hcmute.tlcn.vtc.repository.CommentRepository;
import hcmute.tlcn.vtc.repository.ReviewRepository;
import hcmute.tlcn.vtc.service.user.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ICustomerService customerService;





}
