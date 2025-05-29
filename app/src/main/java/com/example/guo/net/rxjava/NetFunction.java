package com.example.guo.net.rxjava;

import androidx.annotation.NonNull;

import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetFunction implements Function<String, String> {
    @NonNull
    @Override
    public String apply(@NonNull String s) throws Exception {

        OkHttpClient client = NetRequest.getHttpClient();
        Request request = new Request.Builder()
                .url(s)
                .build();
        Response response = client.newCall(request).execute();

        return response.body().string();
    }
}
