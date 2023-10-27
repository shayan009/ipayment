package com.onetechsol.ipayment.pojo;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum PaymentStatusType {


    SUCCESS("payment_successfull"),
    FAILED("payment_failed"),
    BACK_PRESSED("back_pressed"),
    TXN_SESSION_TIMEOUT("txn_session_timeout"),
    USER_CANCELLED("user_cancelled"),
    SERVER_ERROR("error_server_error"),
    ERROR_NO_RETRY("error_noretry"),
    INVALID_INPUT_DATA("invalid_input_data"),
    RETRY_FAIL("retry_fail_error"),
    TRXN_NOT_ALLOWED("trxn_not_allowed"),
    BANK_BACK_PRESSED("bank_back_pressed");


    private static final Map<String, PaymentStatusType> ENUM_MAP;

    static {
        Map<String, PaymentStatusType> map = new ConcurrentHashMap<String, PaymentStatusType>();
        for (PaymentStatusType instance : PaymentStatusType.values()) {
            map.put(instance.paymentStatusType().toLowerCase(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    private String paymentStatusType = "";

    PaymentStatusType(String paymentStatusType) {
        this.paymentStatusType = paymentStatusType;
    }

    public static PaymentStatusType get(String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }

    public String paymentStatusType() {
        return paymentStatusType;
    }
}
