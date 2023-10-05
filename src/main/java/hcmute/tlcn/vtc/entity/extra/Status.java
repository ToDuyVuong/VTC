package hcmute.tlcn.vtc.entity.extra;

import lombok.Getter;

@Getter
public enum Status {

    ACTIVE("Active"),
    INACTIVE("Inactive"),
    DELETED("Deleted"),
    PENDING("Pending"),
    CANCEL("Cancel"),
    SUCCESS("Success"),
    FAIL("Fail"),
    WAITING("Waiting"),
    PROCESSING("Processing"),
    FINISHED("Finished"),
    REFUND("Refund"),
    REFUNDED("Refunded"),
    PAID("Paid"),
    UNPAID("Unpaid");


    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
