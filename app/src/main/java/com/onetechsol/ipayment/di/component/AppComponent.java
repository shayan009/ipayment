package com.onetechsol.ipayment.di.component;

import android.app.Application;

import com.onetechsol.ipayment.app.MainApp;
import com.onetechsol.ipayment.data.locale.pref.PrefManager;
import com.onetechsol.ipayment.di.builder.ActivityBuilder;
import com.onetechsol.ipayment.di.module.AppModule;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;
import com.onetechsol.ipayment.ui.tracking.TrackingService;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(MainApp app);

    void inject(BaseViewModel.Injector app);

    void inject(TrackingService app);

    void inject(PrefManager prefManager);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }
}

