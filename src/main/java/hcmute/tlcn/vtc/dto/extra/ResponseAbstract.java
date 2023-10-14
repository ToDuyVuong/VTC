package hcmute.tlcn.vtc.dto.extra;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract  class ResponseAbstract  {

        private String status;
        private String message;
        private int code;
}
