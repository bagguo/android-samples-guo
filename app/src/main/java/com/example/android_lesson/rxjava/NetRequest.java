package com.example.android_lesson.rxjava;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class NetRequest {
    public static final String TAG = NetRequest.class.getSimpleName();

    public static OkHttpClient client;

    public static OkHttpClient getHttpClient() {
        if (client != null) {
            return client;
        }

//        synchronized (NetRequest.client) {
//            if (client != null) {
//                return client;
//            }

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String s) {
                    Log.d(TAG, "log: ======" + s);
                }
            });
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            client = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build();
//        }
        return client;
    }
}
