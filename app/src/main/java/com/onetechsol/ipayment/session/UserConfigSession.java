package com.onetechsol.ipayment.session;

import com.onetechsol.ipayment.pojo.PageList;
import com.onetechsol.ipayment.pojo.StatusList;
import com.onetechsol.ipayment.pojo.SupportList;

public class UserConfigSession {

    private StatusList statusList;
    private PageList pageList;
    private SupportList supportList;

    public UserConfigSession(StatusList statusList, PageList pageList, SupportList supportList) {
        this.statusList = statusList;
        this.pageList = pageList;
        this.supportList = supportList;
    }

    public StatusList statusList() {
        return statusList;
    }

    public PageList pageList() {
        return pageList;
    }

    public SupportList supportList() {
        return supportList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginSession{");
        sb.append("statusList=").append(statusList);
        sb.append(", pageList=").append(pageList);
        sb.append(", supportList=").append(supportList);
        sb.append('}');
        return sb.toString();
    }
}
