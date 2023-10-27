package com.onetechsol.ipayment.pojo;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ApprovalRatingType {
    NO_RATING("0"),
    EXCELLANT("1"),
    LOW("2"),
    GOOD("3");

    private static final Map<String, ApprovalRatingType> ENUM_MAP;

    static {
        Map<String, ApprovalRatingType> map = new ConcurrentHashMap<>();
        for (ApprovalRatingType instance : ApprovalRatingType.values()) {
            map.put(instance.ratingType().toLowerCase(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    private String ratingType = "";

    ApprovalRatingType(String ratingType) {
        this.ratingType = ratingType;
    }

    public static ApprovalRatingType get(String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }

    public String ratingType() {
        return ratingType;
    }
}
