package hcmute.tlcn.vtc.controller.vendor;

import hcmute.tlcn.vtc.model.data.user.response.ListOrderResponse;
import hcmute.tlcn.vtc.model.extra.Status;
import hcmute.tlcn.vtc.service.vendor.IOrderShopService;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

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


    @GetMapping("/list/on-same-day")
    public ResponseEntity<ListOrderResponse> getOrdersOnSameDate(HttpServletRequest httpServletRequest,
                                                             @RequestParam String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = dateFormat.parse(dateString);

            String username = (String) httpServletRequest.getAttribute("username");
            return ResponseEntity.ok(orderShopService.getOrdersOnSameDay(username, date));
        } catch (ParseException e) {
            throw  new IllegalArgumentException("Ngày không đúng định dạng! Định dạng ngày là dd-MM-yyyy");
        }
    }


    @GetMapping("/list/between-date")
    public ResponseEntity<ListOrderResponse> getOrdersBetweenDate(HttpServletRequest httpServletRequest,
                                                             @RequestParam String startDateString,
                                                             @RequestParam String endDateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date startDate = dateFormat.parse(startDateString);
            Date endDate = dateFormat.parse(endDateString);

            // Chuyển đổi từ Date sang LocalDate
            LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // Kiểm tra xem khoảng thời gian có trong vòng 30 ngày không
            long daysBetween = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
            if (daysBetween > 30) {
                throw new IllegalArgumentException("Khoảng thời gian không được vượt quá 30 ngày.");
            }

            String username = (String) httpServletRequest.getAttribute("username");
            return ResponseEntity.ok(orderShopService.getOrdersBetweenDate(username, startDate, endDate));
        } catch (ParseException e) {
            throw  new IllegalArgumentException("Ngày không đúng định dạng! Định dạng ngày là dd-MM-yyyy");
        }
    }


    @GetMapping("/list/on-same-day/status/{status}")
    public ResponseEntity<ListOrderResponse> getOrdersOnSameDateByStatus(HttpServletRequest httpServletRequest,
                                                                     @RequestParam String dateString,
                                                                     @PathVariable Status status) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = dateFormat.parse(dateString);

            String username = (String) httpServletRequest.getAttribute("username");
            return ResponseEntity.ok(orderShopService.getOrdersOnSameDayByStatus(username, date, status));
        } catch (ParseException e) {
            throw  new IllegalArgumentException("Ngày không đúng định dạng! Định dạng ngày là dd-MM-yyyy");
        }
    }


    @GetMapping("/list/between-date/status/{status}")
    public ResponseEntity<ListOrderResponse> getOrdersBetweenDateByStatus(HttpServletRequest httpServletRequest,
                                                                     @RequestParam String startDateString,
                                                                     @RequestParam String endDateString,
                                                                     @PathVariable Status status) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date startDate = dateFormat.parse(startDateString);
            Date endDate = dateFormat.parse(endDateString);

            // Chuyển đổi từ Date sang LocalDate
            LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // Kiểm tra xem khoảng thời gian có trong vòng 30 ngày không
            long daysBetween = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
            if (daysBetween > 30) {
                throw new IllegalArgumentException("Khoảng thời gian không được vượt quá 30 ngày.");
            }

            String username = (String) httpServletRequest.getAttribute("username");
            return ResponseEntity.ok(orderShopService.getOrdersBetweenDateByStatus(username, startDate, endDate, status));
        } catch (ParseException e) {
            throw  new IllegalArgumentException("Ngày không đúng định dạng! Định dạng ngày là dd-MM-yyyy");
        }
    }



}
