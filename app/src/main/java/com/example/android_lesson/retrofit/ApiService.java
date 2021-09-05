package com.example.android_lesson.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("users/{user}")
    Call<ResponseBody> getUserString(@Path("user") String user);

}
