package hcmute.tlcn.vtc.model.extra;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;

@Getter
@JsonDeserialize(using = StatusDeserializer.class)
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
    UNPAID("Unpaid"),
    CART("Cart"),
    ORDER("Order");


    private final String value;

    Status(String value) {
        this.value = value;
    }

    public static boolean isValidStatus(String status) {
        for (Status validStatus : Status.values()) {
            if (validStatus.name().equalsIgnoreCase(status)) {
                return false;
            }
        }
        return true;
    }

    public static Status fromValue(String value) {
        for (Status status : values()) {
            if (status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        return null;
    }
}
