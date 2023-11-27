package hcmute.tlcn.vtc.service.vendor;

import hcmute.tlcn.vtc.model.data.vendor.request.StatisticsRequest;
import hcmute.tlcn.vtc.model.data.vendor.response.StatisticsResponse;

public interface IRevenueService {
    StatisticsResponse statisticsByDate(StatisticsRequest request);
}
