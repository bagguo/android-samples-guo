package com.example.android_lesson.ui.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.android_lesson.R;
import com.example.android_lesson.net.rxjava.FeedArticleBean;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecyclerViewActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, RecyclerViewActivity.class);
        context.startActivity(intent);
    }

    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView mRecyclerview;
    MyAdapter myAdapter;

    List<FeedArticleBean> mList = new ArrayList<>();

    private MyHandler myHandler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initView();
        initData();
    }

    private void initView() {
        mSwipeRefreshLayout = findViewById(R.id.swiperefreshlayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.purple_700
                , R.color.purple_200, R.color.white, R.color.teal_200);

//        设置下拉刷新时的操作
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData();
            }
        });

        mRecyclerview = findViewById(R.id.recyclerview);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this, mList);
        mRecyclerview.setAdapter(myAdapter);
    }

    private void initData() {
        requestData();
    }

    private void requestData() {
        final String HOST = "https://www.wanandroid.com/";
        String url = HOST + "article/list/0/json";

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();

                try {
                    JSONObject object = JSONObject.parseObject(s);
                    JSONObject data = object.getJSONObject("data");
                    String datas = data.getString("datas");
                    List<FeedArticleBean> articls = JSON.parseArray(datas, FeedArticleBean.class);

                    Message message = new Message();
                    message.obj = articls;
                    message.what = 200;
                    myHandler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


    }

    static class MyHandler extends Handler {
        private WeakReference<RecyclerViewActivity> weakReference;

        public MyHandler(RecyclerViewActivity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    Toast.makeText(weakReference.get(), "failed", Toast.LENGTH_LONG).show();
                    break;
                case 200:
                    List<FeedArticleBean> list = (ArrayList<FeedArticleBean>) msg.obj;
                    weakReference.get().mList.clear();
                    weakReference.get().mList.addAll(list);
                    weakReference.get().myAdapter.notifyDataSetChanged();
                    weakReference.get().mSwipeRefreshLayout.setRefreshing(false);
                    break;
            }

        }
    }
}