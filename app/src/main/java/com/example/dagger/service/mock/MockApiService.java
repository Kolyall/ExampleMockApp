package com.example.dagger.service.mock;

import android.content.Context;

import com.example.R;
import com.example.dagger.service.ApiService;
import com.example.dagger.service.mock.base.BaseMockService;
import com.example.dagger.service.models.DataResponse;
import com.example.dagger.service.models.Login;
import com.example.dagger.service.models.UserProfile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by Nick Unuchek on 03.11.2017.
 */

public class MockApiService extends BaseMockService implements ApiService {
    public MockApiService(Context context, Gson gson) {
        super(context, gson);
    }

    @Override
    public Observable<DataResponse<UserProfile>> signIn(Login body) {
        Type type = new TypeToken<DataResponse<UserProfile>>() {}.getType();
        Observable<DataResponse<UserProfile>> responseFromRes = getResponseFromRes(R.raw.mock_sign_in, type);
        return responseFromRes
                .subscribeOn(Schedulers.io());
    }
}
