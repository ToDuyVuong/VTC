package hcmute.tlcn.vtc.service.manager;

import hcmute.tlcn.vtc.model.data.manager.response.ListShopManagerResponse;
import hcmute.tlcn.vtc.model.extra.Status;

public interface IManagerShopService {
    ListShopManagerResponse getListShop(int size, int page, Status status);

    ListShopManagerResponse getListShopByName(int size, int page, String name, Status status);

    void checkRequestPageParams(int page, int size);
}
