package com.example.push;

import android.content.Context;
import android.util.Log;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.PushAgent;
import com.umeng.message.api.UPushRegisterCallback;

public class PushHelper {
    public static final String TAG = PushHelper.class.getSimpleName();

    public static void init(Context context) {
        UMConfigure.init(context, PushConstants.APP_KEY, PushConstants.CHANNEL,
                UMConfigure.DEVICE_TYPE_PHONE, PushConstants.MESSAGE_SECRET);

//        ...
        //获取消息推送实例
        PushAgent pushAgent = PushAgent.getInstance(context);
        //注册推送服务，每次调用register方法都会回调该接口
        pushAgent.register(new UPushRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                Log.i(TAG, "注册成功：deviceToken：--> " + deviceToken);
            }

            @Override
            public void onFailure(String errCode, String errDesc) {
                Log.e(TAG, "注册失败：--> " + "code:" + errCode + ", desc:" + errDesc);
            }
        });
//        ...
    }
}
