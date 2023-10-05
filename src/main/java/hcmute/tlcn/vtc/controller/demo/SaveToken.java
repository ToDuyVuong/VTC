package hcmute.tlcn.vtc.controller.demo;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
@Hidden
public class SaveToken {

    @Autowired
    private HttpServletResponse response;

    @PostMapping("/send-refresh-token")
    public void sendRefreshToken(HttpServletResponse response) {
        String refreshToken = generateRefreshToken(); // Hãy tự thực hiện logic để tạo refreshToken

        // Lưu refreshToken vào cookie
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/"); // Đặt đúng path mà bạn muốn

        // Set thời gian sống của cookie (ví dụ: 30 ngày)
        cookie.setMaxAge(30 * 24 * 60 * 60);

        // Thêm cookie vào response
        response.addCookie(cookie);
    }

    // Phương thức để tạo refreshToken (bạn cần thực hiện logic tạo refreshToken)
    private String generateRefreshToken() {
        // Thực hiện logic tạo refreshToken
        return "generated_refresh_token"; // Ví dụ: tạo refreshToken ngẫu nhiên
    }

    @PostMapping("/refresh-access-token")
    public ResponseEntity<String> refreshAccessToken(@CookieValue(name = "refreshToken") String refreshToken) {
        // TODO: Thực hiện logic để tạo refreshToken mới dựa trên refreshToken hiện tại
        String newRefreshToken = generateNewRefreshToken(refreshToken);

        // TODO: Lưu newRefreshToken vào cơ sở dữ liệu hoặc nơi thích hợp

        // Trả về newRefreshToken cho client
        return ResponseEntity.ok(newRefreshToken);
    }

    // Phương thức để tạo refreshToken mới (thay thế bằng logic của bạn)
    private String generateNewRefreshToken(String refreshToken) {
        // Thực hiện logic để tạo refreshToken mới
        return "new_generated_refresh_token"; // Ví dụ: tạo refreshToken mới ngẫu nhiên
    }

    @GetMapping("/get-refresh-token")
    public ResponseEntity<String> getRefreshToken(@CookieValue(name = "refreshToken") String refreshToken) {
        // Trả về refreshToken cho client
        return ResponseEntity.ok(refreshToken);
    }


}
