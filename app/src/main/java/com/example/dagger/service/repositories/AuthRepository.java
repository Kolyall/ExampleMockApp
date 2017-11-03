package com.example.dagger.service.repositories;

import com.example.dagger.service.models.Login;
import com.example.dagger.service.models.UserProfile;

import rx.Observable;


/**
 * Created by Nick Unuchek on 03.11.2017.
 */

public interface AuthRepository {
    Observable<UserProfile> signIn(Login login);
}
