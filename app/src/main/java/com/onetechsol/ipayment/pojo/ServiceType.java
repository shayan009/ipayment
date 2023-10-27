package com.onetechsol.ipayment.pojo;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ServiceType {

    BANKING("Banking"),
    MORE("More"),
    RECHARGE_AND_BILLS("Recharge & Bill"),
    INSURANCE("Insurance"),
    SAVING_ACCOUNT("Saving Account"),
    FUND_REQUEST("Fund request"),
    AMAZON_STORE("Amazon Store"),
    PRIME_VIDEO("Prime Video"),
    MYNTRA_STORE("Myntra Store"),
    AJOI_STORE("Ajoi Store"),
    SNAPDEAL_STORE("Snapdeal store"),
    MAADHAR("Maadhaar");

    private static final Map<String, ServiceType> ENUM_MAP;

    static {
        Map<String, ServiceType> map = new ConcurrentHashMap<String, ServiceType>();
        for (ServiceType instance : ServiceType.values()) {
            map.put(instance.getServiceType().toLowerCase(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    private String serviceType = "";

    ServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public static ServiceType get(String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }

    public String getServiceType() {
        return serviceType;
    }

}
