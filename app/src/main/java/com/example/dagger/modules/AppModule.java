package com.example.dagger.modules;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.example.dagger.components.ForApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nikolay Unuchek on 07.09.2016.
 */
@Module
public class AppModule {
    private Application mApplication;

    public AppModule(@NonNull Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @NonNull
    @Singleton
    public Application provideApplication() {
        return mApplication;
    }

    @Provides
    @NonNull
    public Resources provideResources(@ForApplication Context context) {
        return context.getResources();
    }

    @Provides
    public Context provideContext(){
        return mApplication;
    }
}
