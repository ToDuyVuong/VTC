package hcmute.tlcn.vtc.controller.vendor;

import hcmute.tlcn.vtc.model.data.vendor.request.StatisticsRequest;
import hcmute.tlcn.vtc.model.data.vendor.response.StatisticsResponse;
import hcmute.tlcn.vtc.service.vendor.IRevenueService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor/shop/revenue")
@RequiredArgsConstructor
public class RevenueShopController {

    @Autowired
    private IRevenueService revenueService;

    @PostMapping("/statistics")
    public ResponseEntity<StatisticsResponse> statisticsByDate(@RequestBody StatisticsRequest request,
                                                               HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        request.setUsername(username);
        request.validate();
        return ResponseEntity.ok(revenueService.statisticsByDate(request));
    }


}
