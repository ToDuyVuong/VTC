package hcmute.tlcn.vtc.shippingstrategy;

public class GiaoHangNhanhShipping  implements IShipping{
    @Override
    public long calculateShippingCost(Long total) {
        return 30000;
    }
}
