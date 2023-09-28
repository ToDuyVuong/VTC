package hcmute.tlcn.vtc.shippingstrategy;

public class GiaoHangNhanhShipping  implements IShipping{
    @Override
    public long calculateShippingCost(long total) {
        return 30000;
    }
}
