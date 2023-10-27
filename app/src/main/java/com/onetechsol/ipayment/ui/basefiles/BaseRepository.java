package com.onetechsol.ipayment.ui.basefiles;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by MediumMG on 21.02.2018.
 */

public abstract class BaseRepository {

    public <T> Observable<T> getObservable(Observable<T> observable) {
        return observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
