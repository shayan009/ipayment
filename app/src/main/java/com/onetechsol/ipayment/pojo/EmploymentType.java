package com.onetechsol.ipayment.pojo;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum EmploymentType {

    SALARIED("1"),
    SELF_EMPLOYED("2"),
    STUDENT("3");

    private static final Map<String, EmploymentType> ENUM_MAP;

    static {
        Map<String, EmploymentType> map = new ConcurrentHashMap<>();
        for (EmploymentType instance : EmploymentType.values()) {
            map.put(instance.employmentType().toLowerCase(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    private String employmentType = "";

    EmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public static EmploymentType get(String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }

    public String employmentType() {
        return employmentType;
    }
}
