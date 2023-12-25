package hcmute.tlcn.vtc.shippingstrategy;

public class GiaoHangTietKiemShipping implements IShipping{
    @Override
    public long calculateShippingCost(Long total) {
        if (total >= 300000) {
            return 0;
        }
        return 0;
    }

}
