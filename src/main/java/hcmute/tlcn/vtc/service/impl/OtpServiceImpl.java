package hcmute.tlcn.vtc.service.impl;

import hcmute.tlcn.vtc.service.IOtpService;
import hcmute.tlcn.vtc.util.exception.InvalidOtpException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements IOtpService {

    private Map<String, OtpDetails> otpMap = new ConcurrentHashMap<>();


    @Override
    public String generateRandomOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }


    @Override
    public String generateOtp(String username) {
        String otp = generateRandomOtp();
        long expiryTimeMillis = System.currentTimeMillis() + (5 * 60 * 1000);  // 5 minutes validity
        OtpDetails otpDetails = new OtpDetails(otp, expiryTimeMillis);
        otpMap.put(username, otpDetails);

        return otp;
    }

    @Override
    public boolean verifyOtp(String username, String otp) {

        OtpDetails otpDetails = otpMap.get(username);
        if (otpDetails != null && !otpDetails.isExpired()) {
            if (otpDetails.isExpired()) {
                throw new InvalidOtpException("OTP đã hết hạn");
            } else if (!otpDetails.getOtp().equals(otp)) {
                throw new InvalidOtpException("OTP không hợp lệ");
            } else {
                return true;  // OTP hợp lệ
            }
        } else {
            throw new InvalidOtpException("OTP không tồn tại");
        }
    }

    @Override
    public long getTimeValid(String username) {
        OtpDetails otpDetails = otpMap.get(username);

        if (otpDetails != null) {
            long currentTimeMillis = System.currentTimeMillis();
            long expiryTimeMillis = otpDetails.getExpiryTimeMillis();

            if (expiryTimeMillis > currentTimeMillis) {
                return (expiryTimeMillis - currentTimeMillis) / 1000; // Convert to seconds
            }
        }

        return 0; // OTP expired or not found
    }

    @Getter
    private static class OtpDetails {
        private String otp;
        private long expiryTimeMillis;

        public OtpDetails(String otp, long expiryTimeMillis) {
            this.otp = otp;
            this.expiryTimeMillis = expiryTimeMillis;
        }

        public String getOtp() {
            return otp;
        }

        public boolean isExpired() {
            return System.currentTimeMillis() > expiryTimeMillis;
        }
    }
}
