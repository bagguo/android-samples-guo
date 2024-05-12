package com.example.android_lesson.java.queue.messagequeue;

public class HandlerThread extends Thread{

    private Looper mLooper;

    @Override
    public void run() {
        super.run();
        Looper.prepare();
        synchronized (this) {
            mLooper = Looper.myLooper();
            notifyAll();
        }
        Looper.loop();
    }

    public Looper getLooper() {
        if (!isAlive()) {
            return null;
        }

        synchronized (this) {
            while (isAlive() && mLooper == null) {
                try {
                    wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return mLooper;
    }
}
