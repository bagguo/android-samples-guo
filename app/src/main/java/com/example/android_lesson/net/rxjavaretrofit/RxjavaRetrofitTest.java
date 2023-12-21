package com.example.android_lesson.net.rxjavaretrofit;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;

public class RxjavaRetrofitTest {
    private static final String BASE_URL = "http://gank.io/api/";

    public static final String TAG = RxjavaRetrofitTest.class.getSimpleName();

    public void test() {

        OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
        mBuilder.readTimeout(10, TimeUnit.SECONDS);
        mBuilder.connectTimeout(30, TimeUnit.SECONDS);

        HttpLoggingInterceptor mLoggingInterceptor = new HttpLoggingInterceptor();
        mLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        mBuilder.addInterceptor(mLoggingInterceptor);

        OkHttpClient mClient = mBuilder.build();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL);
        builder.client(mClient);

        Retrofit mRetrofit = builder.build();

        final GankApi mGankApi = mRetrofit.create(GankApi.class);
        final Call<ResponseBody> mCall = mGankApi.getJson("10/1");
//
        Observable.create((ObservableOnSubscribe<ResponseBody>) e -> e.onNext(mCall.execute().body()))
                .map(ResponseBody::string)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: d=====" + d);
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: ========" + s);
//                        logContent.setText(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: =======", e);
//                        logContent.setText(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ====");
                    }
                });

//        ApiService service = GenServiceUtil.createService(ApiService.class);
//        final Call<GithubUserBean> call = service.getUser("name");
//        final Observable myObserable = Observable.create(new Observable.OnSubscribe<GithubUserBean>() {
//            @Override
//            public void call(Subscriber<? super GithubUserBean> subscriber) {
//                Response<GithubUserBean> bean = null;
//                try {
//                    bean = call.execute();
//                    subscriber.onNext(bean.body());
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    subscriber.onError(e);
//                }
//
//                subscriber.onCompleted();
//            }
//        });
//
//        myObserable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(new Func1<GithubUserBean, GithubUserBean>() {
//                    @Override
//                    public GithubUserBean call(GithubUserBean o) {
////                        if (TextUtils.isEmpty(o.getBio())) {
////                            o.setBio("nothing !");
////                        }
//                        return o;
//                    }
//                })
//                .subscribe(new Subscriber<GithubUserBean>() {
//                    @Override
//                    public void onCompleted() {
////                        loading.dismiss();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
////                        loading.dismiss();
//                    }
//
//                    @Override
//                    public void onNext(GithubUserBean o) {
////                        setUserView(o);
//                    }
//                });

    }

}
