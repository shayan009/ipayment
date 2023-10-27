package com.onetechsol.ipayment.ui.screen.report;

import com.onetechsol.ipayment.pojo.AEPS1ReportRequest;
import com.onetechsol.ipayment.pojo.AEPS1ReportResponse;
import com.onetechsol.ipayment.pojo.GetReportTypeRequest;
import com.onetechsol.ipayment.pojo.GetReportTypeResponse;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;
import com.onetechsol.ipayment.utils.ApiConstant;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ReportViewModel extends BaseViewModel {


    public Observable<AEPS1ReportResponse> getAEPS1Report(String categoryId, String fromDate, String toDate, String txnType, String txnSubType, String walletType, String txnStatus, String page) {

        AEPS1ReportRequest aeps1ReportRequest = new AEPS1ReportRequest(categoryId, fromDate, toDate, txnType, txnSubType, walletType, txnStatus, page);

        return iModelRepository().getAEPS1Report(aeps1ReportRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<GetReportTypeResponse> getReportCategoryList() {

        return iModelRepository().getReportCategoryList(new GetReportTypeRequest(ApiConstant.BASIC_VERSION))
                .observeOn(AndroidSchedulers.mainThread());
    }


}
