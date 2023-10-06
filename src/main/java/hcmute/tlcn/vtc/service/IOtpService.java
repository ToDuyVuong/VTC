package hcmute.tlcn.vtc.service;

public interface IOtpService {
    String generateRandomOtp();

    String generateOtp(String username);

    void verifyOtp(String username, String otp);

    long getTimeValid(String username);
}
