package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.onetechsol.ipayment.utils.ApiConstant;

public class WalletList {

    @SerializedName("img")
    @Expose
    private String img;

    @SerializedName("btn_lvl")
    @Expose
    private String btnLvl;

    @SerializedName("label")
    @Expose
    private String label;


    @SerializedName("amt")
    @Expose
    private String amt;


    public String img() {
        return ApiConstant.BASE_URL_IMAGE_SERVICE + img;
    }

    public WalletList setImg(String img) {
        this.img = img;
        return this;
    }

    public String btnLvl() {
        return btnLvl;
    }

    public WalletList setBtnLvl(String btnLvl) {
        this.btnLvl = btnLvl;
        return this;
    }

    public String label() {
        return label;
    }

    public WalletList setLabel(String label) {
        this.label = label;
        return this;
    }

    public String amt() {
        return amt;
    }

    public WalletList setAmt(String amt) {
        this.amt = amt;
        return this;
    }
}
