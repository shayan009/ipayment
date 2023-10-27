package com.onetechsol.ipayment.pojo;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ServiceCategoryType {

    AEPS_1("33"),
    AEPS_2("25"),
    AEPS_3("22"),
    MATM("73"),
    PAYOUT("24"),
    DMT("23"),

    RECHARGE_PREPAID("1"),
    RECHARGE_DTH("2"),
    RECHARGE_POSTPAID("3"),
    ELECTRICITY("4"),
    BROADBAND("5"),
    FASTAG_PAYMENT("6"),
    LPG_GAS("8"),
    LOAN("9"),
    PIPED_GAS("10"),
    CREDIT_CARD("11"),
    LANDLINE("12"),
    WATER("13"),
    CABLE_TV("14"),
    CLUBS_ASSOCIATION("15"),
    SUBSCRIPTION("16"),
    MUNICIPAL_TAXES("17"),
    HOUSING_SOCIETY("18"),
    EDUCATION_FEE("19"),
    HOSPITAL("20"),
    NSDL_PAN("38"),
    HOSPITAL_AND("43"),
    RECURRING_DEPOSIT("44"),

    INSURANCE_PERMIUM("7"),
    LIFE_INSURANCE("41"),
    HEALTH_INSURANCE("42"),
    BUY_INSURANCE("70"),


    SAVING_ACCOUNT("1004"),
    FUND_REQUEST("E2"),
    AMAZON_STORE("1005"),
    PRIME_VIDEO("1006"),
    MYNTRA_STORE("1007"),
    AJIO_STORE("1008"),
    SNAPDEAL("1009"),
    MAADHAAR("1010");


    private static final Map<String, ServiceCategoryType> ENUM_MAP;

    static {
        Map<String, ServiceCategoryType> map = new ConcurrentHashMap<String, ServiceCategoryType>();
        for (ServiceCategoryType instance : ServiceCategoryType.values()) {
            map.put(instance.serviceCategoryType().toLowerCase(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    private String serviceCategoryType = "";

    ServiceCategoryType(String serviceCategoryType) {
        this.serviceCategoryType = serviceCategoryType;
    }

    public static ServiceCategoryType get(String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }

    public String serviceCategoryType() {
        return serviceCategoryType;
    }
}
