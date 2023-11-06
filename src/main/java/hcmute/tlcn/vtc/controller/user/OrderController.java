package hcmute.tlcn.vtc.controller.user;

import hcmute.tlcn.vtc.model.data.user.request.CreateOrderUpdateRequest;
import hcmute.tlcn.vtc.model.data.user.response.OrderResponse;
import hcmute.tlcn.vtc.service.user.IOrderService;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestParam List<Long> cartIds,
                                                     HttpServletRequest request) {

        String username = (String) request.getAttribute("username");
        if (cartIds.isEmpty()) {
            throw new NotFoundException("Danh sách mã giỏ hàng không được để trống!");
        }
        return ResponseEntity.ok(orderService.createOrder(username, cartIds));
    }

    @GetMapping("/create-update")
    public ResponseEntity<OrderResponse> createOrderUpdate(@RequestBody CreateOrderUpdateRequest request,
                                                           HttpServletRequest requestHttp) {

        String username = (String) requestHttp.getAttribute("username");
        request.setUsername(username);
        request.validate();
        return ResponseEntity.ok(orderService.createOrderUpdate(request));


    }
}
