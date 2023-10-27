package com.onetechsol.ipayment.pojo;

import java.util.List;

public class AddCustSelectionItemModel {

    private String title;
    private String subTitle;
    private String searchHint;
    private boolean isCancellable;
    private List<AddCustSelectionItem> addCustSelectionItems;

    public AddCustSelectionItemModel(String title, String subTitle, String searchHint, List<AddCustSelectionItem> addCustSelectionItems) {
        this.title = title;
        this.subTitle = subTitle;
        this.searchHint = searchHint;
        this.addCustSelectionItems = addCustSelectionItems;
    }

    public String title() {
        return title;
    }

    public String subTitle() {
        return subTitle;
    }

    public String searchHint() {
        return searchHint;
    }

    public boolean isCancellable() {
        return isCancellable;
    }

    public List<AddCustSelectionItem> addCustSelectionItems() {
        return addCustSelectionItems;
    }
}


