package hcmute.tlcn.vtc.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AppException extends RuntimeException {
    private int code;
    private String message;
}


//public class UserService {
//
//    public User getUser(String username, String password) {
//        User user = userRepository.findByUsername(username);
//        if (user == null)
//            throw new AppException(404, "User not found");
//        else if (!user.getPassword().equals(password))
//            throw new AppException(400, "Password is incorrect");
//        else
//            return user;
//    }
//}



//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
//        try {
//            User user = userService.getUser(username, password);
//            // Xử lý đăng nhập thành công, ví dụ: trả về thông tin người dùng
//            return ResponseEntity.ok("Đăng nhập thành công. ID người dùng: " + user.getId());
//        } catch (AppException ex) {
//            // Xử lý các lỗi xảy ra trong quá trình đăng nhập
//            return ResponseEntity.status(ex.getCode())
//                    .body("Lỗi đăng nhập: " + ex.getMessage());
//        }
//    }


//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDTO> login(@RequestParam String username, @RequestParam String password) {
//        try {
//            User user = userService.getUser(username, password);
//
//            // Tạo một đối tượng UserDTO để chứa thông tin người dùng
//            UserDTO userDTO = new UserDTO();
//            userDTO.setId(user.getId());
//            userDTO.setUsername(user.getUsername());
//            // Các trường thông tin người dùng khác
//
//            // Tạo một đối tượng LoginResponseDTO để trả về thông tin người dùng và không có lỗi
//            LoginResponseDTO responseDTO = new LoginResponseDTO();
//            responseDTO.setUser(userDTO);
//
//            // Trả về đối tượng LoginResponseDTO trong phản hồi thành công
//            return ResponseEntity.ok(responseDTO);
//        } catch (AppException ex) {
//            // Xử lý các lỗi xảy ra trong quá trình đăng nhập
//            LoginResponseDTO errorResponseDTO = new LoginResponseDTO();
//            errorResponseDTO.setErrorMessage("Lỗi đăng nhập: " + ex.getMessage());
//            return ResponseEntity.status(ex.getCode())
//                    .body(errorResponseDTO);
//        }
//    }


//@PostMapping("/login")
//public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
//    try {
//        User user = userService.getUser(username, password);
//
//        // Tạo một đối tượng UserDTO để chứa thông tin người dùng
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(user.getId());
//        userDTO.setUsername(user.getUsername());
//        // Các trường thông tin người dùng khác
//
//        return ResponseEntity.ok(userDTO);
//    } catch (AppException ex) {
//        return ResponseEntity.status(ex.getCode())
//                .body("Lỗi đăng nhập: " + ex.getMessage());
//    }
//}