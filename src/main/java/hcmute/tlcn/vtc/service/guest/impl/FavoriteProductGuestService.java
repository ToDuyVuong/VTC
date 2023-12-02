package hcmute.tlcn.vtc.service.guest.impl;

import hcmute.tlcn.vtc.repository.FavoriteProductRepository;
import hcmute.tlcn.vtc.service.guest.IFavoriteProductGuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteProductGuestService implements IFavoriteProductGuestService {

    @Autowired
    final private FavoriteProductRepository favoriteProductRepository;

    @Override
    public int countFavoriteProduct(Long productId) {
        return favoriteProductRepository.countByProductProductId(productId);
    }

}
