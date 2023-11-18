package hcmute.tlcn.vtc.controller.vendor;

import hcmute.tlcn.vtc.model.data.user.response.ListOrderResponse;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.service.vendor.IOrderShopService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vendor/order")
@RequiredArgsConstructor
public class OrderShopController {

    @Autowired
    private IOrderShopService orderShopService;

    @GetMapping("/list")
    public ResponseEntity<ListOrderResponse> getOrders(HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        return ResponseEntity.ok(orderShopService.getOrders(username));
    }


    @GetMapping("/list/status/{status}")
    public ResponseEntity<ListOrderResponse> getOrdersByStatus(HttpServletRequest httpServletRequest, @PathVariable Status status) {
        String username = (String) httpServletRequest.getAttribute("username");
        return ResponseEntity.ok(orderShopService.getOrdersByStatus(username, status));
    }


}
