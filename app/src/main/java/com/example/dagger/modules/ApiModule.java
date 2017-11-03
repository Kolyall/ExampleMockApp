package com.example.dagger.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.dagger.service.ApiService;
import com.example.dagger.service.mock.MockApiService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nikolay Unuchek on 08.09.2016.
 */
@Module
public class ApiModule {
    public static final String KEY_API = "API";
    public static final String KEY_MOCK_API = "MOCK_API";


    @Provides
    @Singleton
    public Gson provideDefaultLowerCaseGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setLenient()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();
    }

    @Provides
    @NonNull
    @Singleton
    public Interceptor providesHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }


    @Provides
    @NonNull
    @Singleton
    public OkHttpClient providesCallOkHttpClient(Interceptor loggingInterceptor) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor);

        return httpClient.build();
    }

    @Provides
    @Named(KEY_API)
    @NonNull
    @Singleton
    public ApiService providesApiService(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("https://production.by/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApiService.class);
    }

    @Provides
    @Named(KEY_MOCK_API)
    @NonNull
    @Singleton
    public ApiService providesMockApiService(Context context, Gson gson) {
        return new MockApiService(context,gson);
    }

}