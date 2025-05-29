package com.example.guo.net.retrofit;

import android.util.Log;

import com.example.guo.util.JsonFormatUtil;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitTest {
    public static final String TAG = RetrofitTest.class.getSimpleName();

    public static final String BASE_URL =  "https://api.github.com/";

    public void test() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL);

        Retrofit retrofit = builder
                .client(httpClient.build())
                .build();

        ApiService simpleService = retrofit.create(ApiService.class);
        Call<ResponseBody> call = simpleService.getUserString("name");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                loading.dismiss();
                try {
                    ResponseBody body = response.body();
                    if (body == null) {
                        Log.d(TAG, "onResponse: body null========");
                        return;
                    }

                    String result = body.string();
                    Log.d(TAG, "<--onResponse: \n" + JsonFormatUtil.INSTANCE.formatDataFromJson(result));
//                    Gson gson = new Gson();
//                    GithubUserBean bean = gson.fromJson(result, GithubUserBean.class);
//                    setUserView(bean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                loading.dismiss();
                Log.e(TAG, "onFailure: ======", t);
            }
        });
    }
}
