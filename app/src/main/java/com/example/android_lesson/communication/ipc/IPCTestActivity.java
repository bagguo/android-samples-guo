package com.example.android_lesson.communication.ipc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.android_lesson.R;
import com.example.android_lesson.communication.ipc.aidl.AIDLClientActivity;
import com.example.android_lesson.communication.ipc.binder.bigbitmap.ImageBinder;
import com.example.android_lesson.communication.ipc.binder.bigbitmap.ReceiverBigBitmapActivity;

public class IPCTestActivity extends AppCompatActivity {

    public static void start(Context context){
        Intent intent = new Intent(context, IPCTestActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc_test_activity);

        findViewById(R.id.btn_aidl).setOnClickListener(vew -> {
            Intent intent = new Intent(IPCTestActivity.this, AIDLClientActivity.class);
            startActivity(intent);
        });

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.elephant, null);
        ImageView iv = findViewById(R.id.iv);
        iv.setImageDrawable(drawable);

        findViewById(R.id.btn_send_big_bitmap).setOnClickListener(vew -> {
            Bundle bundle = new Bundle();
            bundle.putBinder("binder_bitmap", new ImageBinder(drawable));

            Intent intent = new Intent(IPCTestActivity.this, ReceiverBigBitmapActivity.class);
            intent.putExtra("big_bitmap_bundle", bundle);
            startActivity(intent);
        });
    }
}