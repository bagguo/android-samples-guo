package com.example.android_lesson.service.ipc.aidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android_lesson.IMyAidlInterface;
import com.example.android_lesson.R;

public class ClientActivity extends AppCompatActivity {

    private static final String TAG = ClientActivity.class.getSimpleName();

    private IMyAidlInterface iMyAidlInterface;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    private EditText et_num1;
    private EditText et_num2;
    private TextView tv_total;
    private Button btn_add;
    private int nNum1;
    private int nNum2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        bindService2();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }

    private void initView() {
        et_num1 = findViewById(R.id.et_num1);
        et_num2 = findViewById(R.id.et_num2);
        tv_total = findViewById(R.id.tv_total);
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(view -> {
            handleBtnClickEvent();
        });
    }

    private void bindService2() {
        Intent intent = new Intent(this, RemoteService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void handleBtnClickEvent() {
        nNum1 = Integer.parseInt(et_num1.getText().toString());
        nNum2 = Integer.parseInt(et_num2.getText().toString());

        int total = 0;
        try {
            total = iMyAidlInterface.add(nNum1, nNum2);
            Log.d(TAG, "onCreate: ======total:" + total);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        tv_total.setText(total + "");
    }

}