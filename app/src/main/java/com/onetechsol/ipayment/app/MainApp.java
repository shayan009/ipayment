package com.onetechsol.ipayment.app;

import android.app.Activity;
import android.app.Application;
import android.app.Service;

import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo;
import com.onetechsol.ipayment.di.component.AppComponent;
import com.onetechsol.ipayment.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;
import okhttp3.OkHttpClient;

public class MainApp extends Application implements HasActivityInjector, HasServiceInjector {

    private static MainApp context;
    private static AppComponent appComponent;
    @Inject
    public DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
    @Inject
    public OkHttpClient client;
    @Inject
    public DispatchingAndroidInjector<Service> dispatchingServiceInjector;

    public static MainApp getContext() {
        return context;
    }

    public static AppComponent appComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build();
        appComponent.inject(this);
        RxPaparazzo.register(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return dispatchingServiceInjector;
    }
}
