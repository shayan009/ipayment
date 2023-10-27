package com.onetechsol.ipayment.pojo;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum WalletType {

    COMMISSION("Commission Wallet"),
    MAIN("Main Wallet"),
    SETTLEMENT("Settlement Wallet");

    private static final Map<String, WalletType> ENUM_MAP;

    static {
        Map<String, WalletType> map = new ConcurrentHashMap<String, WalletType>();
        for (WalletType instance : WalletType.values()) {
            map.put(instance.getWalletType().toLowerCase(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    private String walletType = "";

    WalletType(String walletType) {
        this.walletType = walletType;
    }

    public static WalletType get(String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }

    public String getWalletType() {
        return walletType;
    }

}
