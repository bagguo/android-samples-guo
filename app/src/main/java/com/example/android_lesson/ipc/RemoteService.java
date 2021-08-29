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

public class RemoteService extends Service {

    public static final String TAG = RemoteService.class.getSimpleName();

    private MyBinder mBinder;
    private boolean mIsBoundLocalService;

    public RemoteService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        if (mBinder == null) {
            mBinder = new MyBinder();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: ");
        try {
            Intent remoteService = new Intent(RemoteService.this, LocalService.class);
            mIsBoundLocalService = bindService(remoteService, connection, Context.BIND_ABOVE_CLIENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return START_STICKY;
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
                    if (mIsBoundLocalService) {
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
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e(TAG, "onServiceDisconnected: ");
            if (ServiceUtils.isRunningTaskExist(getApplicationContext(), getPackageName() + ":remote")) {
                Intent localService = new Intent(RemoteService.this, LocalService.class);
                startService(localService);

                Intent intent = new Intent(RemoteService.this, LocalService.class);
                bindService(intent, connection, Context.BIND_ABOVE_CLIENT);
            }

        }
    };
}