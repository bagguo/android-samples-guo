package com.example.guo.ui.multitype.normal;

import android.os.Bundle;

import com.example.guo.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class NormalActivity extends AppCompatActivity {

    private MultiTypeAdapter adapter;
    private Items items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        RecyclerView recyclerView = findViewById(R.id.list);

        adapter = new MultiTypeAdapter();
        adapter.register(TextItem.class, new TextItemViewBinder());
        recyclerView.setAdapter(adapter);

        TextItem textItem = new TextItem("world");

        items = new Items();
        for (int i = 0; i < 20; i++) {
            items.add(textItem);
        }
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }
}