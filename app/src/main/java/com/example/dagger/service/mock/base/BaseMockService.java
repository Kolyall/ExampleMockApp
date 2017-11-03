package com.example.dagger.service.mock.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RawRes;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;

import rx.Observable;
import rx.schedulers.Schedulers;


public class BaseMockService {

    Context mContext;
    Gson mGson;
    public BaseMockService(Context context, Gson gson) {
        this.mContext =context;
        this.mGson =gson;
    }


    @NonNull
    protected <T> Observable<T> getResponseFromRes(@RawRes int rawId, final Type type) {
        return getStringFromRawResObservable(rawId)
                .map(jsonString -> getJson(jsonString,type));
    }


    @NonNull
    private Observable<String> getStringFromRawResObservable(@RawRes final int rawId) {
            return Observable.fromCallable(() -> readJsonFromRes(rawId))
                    .subscribeOn(Schedulers.io());
    }

    private String readJsonFromRes(int rawId) throws IOException {
        InputStream is = mContext.getResources().openRawResource(rawId);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            is.close();
        }

        return writer.toString();
    }


    private <T> T getJson(String json,Type type) {
        return mGson.fromJson(json, type);
    }
}
