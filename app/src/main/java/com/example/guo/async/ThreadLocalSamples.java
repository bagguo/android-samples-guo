package com.example.guo.async;

import android.util.Log;

public class ThreadLocalSamples {

    static ThreadLocal<Integer> sThreadLocal = new ThreadLocal<>();

    int num = 0;

    public void testThreadLocal() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sThreadLocal.set(num);

                    for (int i = 0; i < 1000; i++) {
                        num++;
                    }

                    //打印结果：0，num没有被子线程修改
                    Log.d("ThreadSamples", "test: ======" + sThreadLocal.get());
                } finally { //threadLocal移除
                    sThreadLocal.remove();
                }
            }
        }).start();
    }

}
