package com.onetechsol.ipayment.ui.screen.home;


import android.net.Uri;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.app.MainApp;
import com.onetechsol.ipayment.databinding.FragmentHomeBinding;
import com.onetechsol.ipayment.pojo.GetAffiliateServiceList;
import com.onetechsol.ipayment.pojo.GetDepartmentListResponse;
import com.onetechsol.ipayment.pojo.ServiceListRequest;
import com.onetechsol.ipayment.pojo.ServiceListResponse;
import com.onetechsol.ipayment.pojo.WalletListRequest;
import com.onetechsol.ipayment.pojo.WalletListResponse;
import com.onetechsol.ipayment.ui.adapter.GromoReviewAdapter;
import com.onetechsol.ipayment.ui.adapter.GromoTutorialAdapter;
import com.onetechsol.ipayment.ui.adapter.ScreenSlidePagerAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;
import com.onetechsol.ipayment.utils.ApiConstant;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class HomeFragmentViewModel extends BaseViewModel {


    public Observable<ServiceListResponse> getServiceList() {

        ServiceListRequest serviceListRequest = new ServiceListRequest(ApiConstant.BASIC_VERSION);

        return iModelRepository().getServiceList(serviceListRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<GetDepartmentListResponse> getDepartmentList() {

        return iModelRepository().getDepartmentList()
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<GetAffiliateServiceList> getAffiliateServiceList() {

        return iModelRepository().getAffiliateList()
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<WalletListResponse> getWalletList(String bearerAuth, String userName) {

        WalletListRequest walletListRequest = new WalletListRequest(ApiConstant.BASIC_VERSION);


        return iModelRepository().getWalletList(walletListRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void getHomeFragmentData(FragmentHomeBinding fragmentHomeBinding, ScreenSlidePagerAdapter viewPagerAdapter, GromoTutorialAdapter gromoTutorialAdapter, GromoReviewAdapter gromoReviewAdapter) {


        Uri uriCC = Uri.parse("https://firebasestorage.googleapis.com/v0/b/affiliate-api-63d14.appspot.com/o/dematac.png?alt=media&token=99fff911-29f9-4fc9-af1c-662204d7d0e6");
        Uri uriSaving = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.ic_saving);
        Uri uriInsurance = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.ic_insurance);
        Uri uriDemat = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.dematac);


    }

}
