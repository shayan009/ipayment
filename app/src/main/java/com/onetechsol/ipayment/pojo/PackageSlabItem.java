package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Transient;

public class PackageSlabItem {

    @Transient
    private String id;

    @SerializedName("ServiceProvider")
    @Expose
    private String range;

    @SerializedName("Service")
    @Expose
    private String name;

    @SerializedName("Service2")
    @Expose
    private String service;

    @SerializedName("commission")
    @Expose
    private String commission;

    @SerializedName("commissionType")
    @Expose
    private String charge;

    public PackageSlabItem(String id, String range, String name, String service, String commission, String charge) {
        this.id = id;
        this.range = range;
        this.name = name;
        this.service = service;
        this.commission = commission;
        this.charge = charge;
    }


    public String id() {
        return id;
    }

    public PackageSlabItem setId(String id) {
        this.id = id;
        return this;
    }

    public String range() {
        return range;
    }

    public PackageSlabItem setRange(String range) {
        this.range = range;
        return this;
    }

    public String name() {
        return name;
    }

    public PackageSlabItem setName(String name) {
        this.name = name;
        return this;
    }

    public String service() {
        return service;
    }

    public PackageSlabItem setService(String service) {
        this.service = service;
        return this;
    }

    public String commission() {
        return commission;
    }

    public PackageSlabItem setCommission(String commission) {
        this.commission = commission;
        return this;
    }

    public String charge() {
        return charge;
    }

    public PackageSlabItem setCharge(String charge) {
        this.charge = charge;
        return this;
    }
}
