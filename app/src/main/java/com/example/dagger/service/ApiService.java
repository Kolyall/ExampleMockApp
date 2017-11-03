package com.example.dagger.service;

import com.example.dagger.service.models.DataResponse;
import com.example.dagger.service.models.Login;
import com.example.dagger.service.models.UserProfile;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

import static com.example.dagger.service.ApiConstants.APIv1_;
import static com.example.dagger.service.ApiConstants.PROFILE_;
import static com.example.dagger.service.ApiConstants.SIGN_IN;

/**
 * Created by Nick Unuchek on 03.11.2017.
 */

public interface ApiService {

    // api/v1/profile/sign_in
    @POST(APIv1_ + PROFILE_ + SIGN_IN)
    Observable<DataResponse<UserProfile>> signIn(@Body Login body);
}
