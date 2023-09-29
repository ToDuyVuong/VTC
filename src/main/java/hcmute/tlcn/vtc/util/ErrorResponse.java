package hcmute.tlcn.vtc.util;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private HttpStatus status;
    private String message;
    private int code;

    public ErrorResponse(HttpStatus status, int code, String title, String message) {
        this.status = status;
        this.code = code;
        this.message = title + ": " + message;
    }
}
