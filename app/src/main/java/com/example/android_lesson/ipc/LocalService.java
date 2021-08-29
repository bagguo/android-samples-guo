package com.example.android_lesson.ipc;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.android_lesson.IGuardAidl;
import com.example.android_lesson.ipc.utils.ServiceUtils;

public class LocalService extends Service {

    public static final String TAG = LocalService.class.getSimpleName();

    private MyBinder mBinder;
    private boolean mIsBoundRemoteService;

    public LocalService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: ");
        if (mBinder == null) {
            mBinder = new MyBinder();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: ");
        return START_STICKY;//粘性服务
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: ");
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
        if (connection != null) {
            try {
                if (mIsBoundRemoteService) {
                    unbindService(connection);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private final class MyBinder extends IGuardAidl.Stub {
        @Override
        public void wakeUp(String title, String description, int iconRes) throws RemoteException {

            Log.e(TAG, "wakeUp: ");
        }
    }


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e(TAG, "onServiceConnected: ");
            try {
                IGuardAidl iGuardAidl = IGuardAidl.Stub.asInterface(iBinder);
                iGuardAidl.wakeUp("title", "des", 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e(TAG, "onServiceDisconnected: ");
            if (ServiceUtils.isServiceRunning(getApplicationContext(), "com.example.android_lesson.ipc.LocalService")) {
                Intent remoteService = new Intent(LocalService.this, RemoteService.class);
                startService(remoteService);

                Intent intent = new Intent(LocalService.this, RemoteService.class);
                mIsBoundRemoteService = bindService(intent, connection, Context.BIND_ABOVE_CLIENT);
            }
        }
    };
}