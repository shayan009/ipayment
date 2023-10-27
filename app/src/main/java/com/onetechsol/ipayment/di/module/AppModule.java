package com.onetechsol.ipayment.di.module;

import com.androidnetworking.gsonparserfactory.GsonParserFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.onetechsol.ipayment.app.MainApp;
import com.onetechsol.ipayment.data.locale.pref.PrefManager;
import com.onetechsol.ipayment.data.remote.network.ErrorResponseInterceptor;
import com.onetechsol.ipayment.data.remote.network.MyOkHttpInterceptor;
import com.onetechsol.ipayment.data.remote.network.retrofit.RetrofitService;
import com.onetechsol.ipayment.di.UnsafeOkHttpClient;
import com.onetechsol.ipayment.service.repository.IModelRepository;
import com.onetechsol.ipayment.service.repository.ModelRepositoryImpl;
import com.onetechsol.ipayment.utils.ApiConstant;
import com.onetechsol.ipayment.widgets.NotificationUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {


    @Provides
    @Singleton
    IModelRepository provideDataRepository(RetrofitService retrofitService) {
        return new ModelRepositoryImpl(retrofitService);
    }


    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder().setLenient().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    }


    @Provides
    @Singleton
    ErrorResponseInterceptor provideErrorResponseInterceptor() {
        return new ErrorResponseInterceptor();
    }


    @Provides
    @Singleton
    PrefManager provideMyPreference() {
        return new PrefManager(MainApp.getContext().getApplicationContext());
    }


    @Provides
    @Singleton
    NotificationUtils provideNotificationUtils() {
        return new NotificationUtils(MainApp.getContext().getApplicationContext());
    }


    @Provides
    CompositeDisposable provideDiscomposer() {
        return new CompositeDisposable();
    }


    @Provides
    @Singleton
    MyOkHttpInterceptor provideCacheControlResponeInterceptor(PrefManager prefManager) {
        return new MyOkHttpInterceptor(prefManager);
    }


    @Provides
    @Singleton
    GsonParserFactory provideGsonParserFactory(Gson gson) {
        return new GsonParserFactory(gson);
    }

    @Provides
    @Singleton
    Converter.Factory provideConverterFactory() {
        Gson gson = new GsonBuilder()
                .create();
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    RetrofitService provideRetrofit(OkHttpClient okHttpClient, Converter.Factory converterFactory) {

        Retrofit build = new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(converterFactory)
                .client(okHttpClient)
                .build();
        return build.create(RetrofitService.class);


    }


    @Provides
    @Singleton
    HttpLoggingInterceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }


    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(MyOkHttpInterceptor myOkHttpInterceptor, HttpLoggingInterceptor loggingInterceptor) {

     /*   okhttp3.logging.HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);*/
/*
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(120, TimeUnit.SECONDS);
        client.readTimeout(120, TimeUnit.SECONDS);
        client.writeTimeout(120, TimeUnit.SECONDS);*/

        OkHttpClient.Builder builder = UnsafeOkHttpClient.getUnsafeOkHttpClient()
                .connectTimeout(ApiConstant.TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(ApiConstant.TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(ApiConstant.TIMEOUT_WRITE, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(myOkHttpInterceptor);

        return builder.build();
    }

}
