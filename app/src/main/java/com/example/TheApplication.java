package com.example;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.example.dagger.components.AppComponent;
import com.example.dagger.components.DaggerAppComponent;
import com.example.dagger.modules.AppModule;


/**
 * Created by Nick Unuchek (skype: kolyall) on 09.03.2017.
 */

public class TheApplication extends MultiDexApplication {
    private static Context context;
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        setupComponent();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    private void setupComponent() {
        component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getComponent() {
        if (component==null)
            setupComponent();
        return component;
    }

    public static AppComponent getAppComponent() {
        return get().getComponent();
    }

    public static AppComponent getComponent(Context context) {
        return ((TheApplication) context.getApplicationContext()).getComponent();
    }

    public static Context getContext() {
        return context;
    }

    public static TheApplication get() {
        return (TheApplication)getContext().getApplicationContext();
    }
}
