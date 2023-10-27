package com.onetechsol.ipayment.ui.screen.packagekit;

import com.onetechsol.ipayment.pojo.BuyKitRequestRequest;
import com.onetechsol.ipayment.pojo.BuyKitResponse;
import com.onetechsol.ipayment.pojo.GetKitListResponse;
import com.onetechsol.ipayment.pojo.GetKitRequest;
import com.onetechsol.ipayment.pojo.GetPackageSlabRequest;
import com.onetechsol.ipayment.pojo.GetPackageSlabResponse;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class PackageKitViewModel extends BaseViewModel {

    public Observable<GetKitListResponse> getKitList() {

        return iModelRepository().getKitList(new GetKitRequest(""))
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<GetPackageSlabResponse> myPackageList(String packageId) {

        return iModelRepository().myPackageList(new GetPackageSlabRequest(packageId))
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BuyKitResponse> buyKit(String packageName, String kitQuantity, String username) {

        return iModelRepository().buyKit(new BuyKitRequestRequest(packageName, kitQuantity, username))
                .observeOn(AndroidSchedulers.mainThread());
    }

}
