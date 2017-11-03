package com.example.dagger.service.repositories;

import com.example.dagger.service.ApiService;
import com.example.dagger.service.models.DataResponse.Status;
import com.example.dagger.service.models.Login;
import com.example.dagger.service.models.UserProfile;

import rx.Observable;

/**
 * Created by Nick Unuchek on 03.11.2017.
 */

public class ApiAuthRepository implements AuthRepository {

    ApiService mApiService;

    public ApiAuthRepository(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<UserProfile> signIn(Login login) {
        return mApiService.signIn(login)
                .filter(response -> response!=null)
                .filter(response -> response.getStatus().equals(Status.success))
                .map(response -> response.getData())
                .filter(data -> data!=null);
    }
}
