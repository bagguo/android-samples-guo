package com.example.android_lesson.rxjava;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;


import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 实际案例中该类应该是一个fragment或Activity
 * 请求完做UI展示
 */
public class RxJavaTest {
    public static final String TAG = RxJavaTest.class.getSimpleName();

    public void test(Context context) {
        final String HOST = "https://www.wanandroid.com/";
        String url = HOST + "article/list/0/json";

        /**
         * 请求方式2
         * rxjava + okhttp
         *
         * 1.被观察者发布事件流 implements Function<String, String>.apply(String s)
         * 2.事件完成后 观察者 订阅收到响应结果 implements Observer {
         *     onSubscribe(Disposable d) {}
         *     onNext(Object o) {}
         *     onError(Throwable e) {}
         *     onComplete() {}
         * }
         */
        Observable.just(url)
                .map(new NetFunction())//网络请求
                .map(new Function<String, List<FeedArticleBean>>() {
                    @NonNull
                    @Override
                    public List<FeedArticleBean> apply(@NonNull String s) throws Exception {
                        Log.d(TAG, "apply: ======" + s);

                        JSONObject object = JSONObject.parseObject(s);
                        JSONObject data = object.getJSONObject("data");
                        String list = data.getString("datas");
                        List<FeedArticleBean> articles = JSONObject.parseObject(list, (Type) FeedArticleBean.class);
                        return articles;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//观察者指定主线程
                .subscribeOn(Schedulers.io())//订阅
                .subscribe(new NetObserver(context) {
                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        Log.d(TAG, "onNext: ====" + o.toString());
//                        fillData((List<FeedArticleBean>) o);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        Log.d(TAG, "onError: ========" +e.toString());
//                        showError(e.getMessage());
                    }
                });
    }
}