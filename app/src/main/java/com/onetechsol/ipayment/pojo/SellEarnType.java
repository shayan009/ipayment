package com.onetechsol.ipayment.pojo;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum SellEarnType {


    SUBSCRIPTION("25"),
    PERSONAL_LOAN("26"),
    INSTANT_LOAN("28"),
    INVESTMENT("23"),
    INSURANCE("30"),
    UPI("29"),
    SAVINGS_ACC("22"),
    DEMAT_ACC("24"),
    CREDIT_CARD("21"),
    CREDIT_LINE("27");


    private static final Map<String, SellEarnType> ENUM_MAP;

    static {
        Map<String, SellEarnType> map = new ConcurrentHashMap<String, SellEarnType>();
        for (SellEarnType instance : SellEarnType.values()) {
            map.put(instance.sellEarnType().toLowerCase(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    private String sellEarnType = "";

    SellEarnType(String sellEarnType) {
        this.sellEarnType = sellEarnType;
    }

    public static SellEarnType get(String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }

    public String sellEarnType() {
        return sellEarnType;
    }
}
