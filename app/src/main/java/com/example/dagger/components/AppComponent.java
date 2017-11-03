package com.example.dagger.components;

import com.example.dagger.modules.ApiModule;
import com.example.dagger.modules.ApiRepositoriesModule;
import com.example.dagger.modules.AppModule;
import com.example.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Nikolay Unuchek on 07.09.2016.
 */
@ForApplication
@Singleton
@Component(
        modules = {
                AppModule.class,
                ApiModule.class,
                ApiRepositoriesModule.class,
        })
public interface AppComponent {

    void inject(MainActivity mainActivity);
}