package com.example.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.R;
import com.example.TheApplication;
import com.example.dagger.service.models.Login;
import com.example.dagger.service.models.UserProfile;
import com.example.dagger.service.repositories.AuthRepository;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

import static com.example.dagger.modules.ApiRepositoriesModule.KEY_MOCK_REPOSITORY;

/**
 * Created by Nick Unuchek on 03.11.2017.
 */

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.emailEditText) EditText mEmailEditText;
    @BindView(R.id.passwordEditText) EditText mPasswordEditText;

    @Inject @Named(KEY_MOCK_REPOSITORY) AuthRepository mAuthRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        TheApplication.getAppComponent().inject(this);
    }

    @OnClick(R.id.signInButton)
    public void onViewClicked() {
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        Login login = new Login(email, password);

        mAuthRepository.signIn(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        Log.d(TAG, "doOnSubscribe: show any progress");
                    }
                })
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        Log.d(TAG, "doOnTerminate: hide any progress");
                    }
                })
                .subscribe(new Subscriber<UserProfile>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserProfile userProfile) {
                        Log.d(TAG, "onNext: "+ userProfile.toString());
                        Toast.makeText(MainActivity.this, "Success login, User Name:" + userProfile.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
