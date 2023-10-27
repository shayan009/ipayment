package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OthersList {

    @SerializedName("news")
    @Expose
    private String news;


    public OthersList() {
    }

    public OthersList(String news) {
        this.news = news;
    }

    public String news() {
        return news;
    }

    public OthersList setNews(String news) {
        this.news = news;
        return this;
    }
}
