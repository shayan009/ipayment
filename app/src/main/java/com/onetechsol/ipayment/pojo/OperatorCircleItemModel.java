package com.onetechsol.ipayment.pojo;

import java.util.List;
import java.util.StringJoiner;

public class OperatorCircleItemModel {

    private String title;
    private String subTitle;
    private String searchHint;

    private boolean isCancellable;
    private List<OpCircleItemDto> opCircleItemDtoList;

    public OperatorCircleItemModel(boolean isCancellable, String title, String subTitle, String searchHint, List<OpCircleItemDto> opCircleItemDtoList) {
        this.title = title;
        this.isCancellable = isCancellable;
        this.subTitle = subTitle;
        this.searchHint = searchHint;
        this.opCircleItemDtoList = opCircleItemDtoList;
    }

    public boolean isCancellable() {
        return isCancellable;
    }

    public OperatorCircleItemModel setCancellable(boolean cancellable) {
        isCancellable = cancellable;
        return this;
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

    public void setOpCircleItemDtoList(List<OpCircleItemDto> opCircleItemDtoList) {
        this.opCircleItemDtoList = opCircleItemDtoList;
    }

    public List<OpCircleItemDto> opCircleItemDtoList() {
        return opCircleItemDtoList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OperatorCircleItemModel.class.getSimpleName() + "[", "]")
                .add("title='" + title + "'")
                .add("subTitle='" + subTitle + "'")
                .add("searchHint='" + searchHint + "'")
                .add("opCircleItemDtoList=" + opCircleItemDtoList)
                .toString();
    }
}
