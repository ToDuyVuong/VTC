package hcmute.tlcn.vtc.service.guest.impl;

import hcmute.tlcn.vtc.repository.FollowedShopRepository;
import hcmute.tlcn.vtc.service.guest.IFollowedGuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowedGuestServiceImpl implements IFollowedGuestService {

    @Autowired
    private final FollowedShopRepository followedShopRepository;

    @Override
    public int countFollowedShop(Long shopId) {
        return followedShopRepository.countByShopShopId(shopId);
    }



}
