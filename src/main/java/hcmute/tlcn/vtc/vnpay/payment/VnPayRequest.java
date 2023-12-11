package hcmute.tlcn.vtc.vnpay.payment;

import lombok.Data;

import java.security.MessageDigest;

@Data
public class VnPayRequest {
    private String vnp_Version;
    private String vnp_Command;
    private String vnp_TmnCode;
    private String vnp_Amount;
    private String vnp_CurrCode;
    private String vnp_OrderInfo;
    private String vnp_TxnRef;
    private String vnp_IpAddr;
    private String vnp_ReturnUrl;
    private String vnp_CreateDate;
    private String vnp_SecureHash;

    // Các getter và setter cho các trường dữ liệu

    public String createHashData(String secretKey) {
        try {
            // Tạo chuỗi dữ liệu cần tạo mã hash
            String data = vnp_TmnCode + vnp_Amount + vnp_Command + vnp_CreateDate + vnp_CurrCode + vnp_IpAddr + vnp_OrderInfo + vnp_ReturnUrl + vnp_TxnRef + vnp_Version;

            // Thực hiện tạo mã hash bằng thuật toán MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes());
            byte[] byteData = md.digest();

            // Chuyển đổi byteData thành dạng chuỗi hex
            StringBuilder sb = new StringBuilder();
            for (byte b : byteData) {
                sb.append(String.format("%02x", b));
            }

            // Trả về chuỗi mã hash đã tạo
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getVnp_Url() {
        // Xây dựng URL dựa trên các trường dữ liệu
        // Ví dụ: "?vnp_Version=" + vnp_Version + "&vnp_Command=" + vnp_Command + ...

        return "url";
    }
}