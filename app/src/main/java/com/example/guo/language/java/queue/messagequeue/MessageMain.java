package com.example.guo.language.java.queue.messagequeue;

public class MessageMain {

    public static void main(String[] args) {
        Looper.prepareMainThread();
        HandlerThread handlerThread = new HandlerThread();
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        System.out.println("1、创建Runnable的线程id = " + Thread.currentThread().getId());
        handler.post(new Runnable() {
            @Override
            public void run() {
                System.out.println("1、执行Runnable的线程id = " + Thread.currentThread().getId());
            }
        });

        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2、创建Runnable的线程id = " + Thread.currentThread().getId());
                Handler main = new Handler(Looper.getMainLooper());
                main.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("2、执行Runnable的线程id = " + Thread.currentThread().getId());
                        System.exit(0);
                    }
                });
            }
        });
        Looper.loop();
    }
}
