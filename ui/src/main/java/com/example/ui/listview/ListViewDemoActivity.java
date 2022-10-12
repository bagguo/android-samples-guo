package com.example.ui.listview;

import android.os.Bundle;
import android.widget.ListView;

import com.example.ui.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class ListViewDemoActivity extends AppCompatActivity {

    private final List<String> mList = new ArrayList<>();
    private ListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_demo);

        initView();
        initData();
    }

    private void initData() {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mList.add("item1");
            mList.add("item2");
            mList.add("item3");

            runOnUiThread(() -> mAdapter.notifyDataSetChanged());
        }).start();

    }

    private void initView() {
        ListView listView = findViewById(R.id.list_view);

        mAdapter = new ListViewAdapter(this, mList);
        listView.setAdapter(mAdapter);
    }

}