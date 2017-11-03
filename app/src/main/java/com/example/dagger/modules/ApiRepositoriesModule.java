package com.example.dagger.modules;

import android.support.annotation.NonNull;

import com.example.dagger.service.ApiService;
import com.example.dagger.service.repositories.ApiAuthRepository;
import com.example.dagger.service.repositories.AuthRepository;
import com.example.dagger.service.repositories.MockAuthRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.example.dagger.modules.ApiModule.KEY_API;
import static com.example.dagger.modules.ApiModule.KEY_MOCK_API;

/**
 * Created by Nikolay Unuchek on 07.09.2016.
 */
@Module
public class ApiRepositoriesModule {

    public static final String KEY_API_REPOSITORY = "key_api_repository";
    public static final String KEY_MOCK_REPOSITORY = "key_mock_repository";

    @Provides
    @NonNull
    @Named(KEY_API_REPOSITORY)
    @Singleton
    public AuthRepository providesApiAuthRepository(@Named(KEY_API)ApiService apiService) {
        return new ApiAuthRepository(apiService);
    }

    @Provides
    @NonNull
    @Named(KEY_MOCK_REPOSITORY)
    @Singleton
    public AuthRepository providesMockAuthRepository(@Named(KEY_MOCK_API)ApiService apiService) {
        return new MockAuthRepository(apiService);
    }
}
