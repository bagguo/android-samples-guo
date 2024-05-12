package com.example.android_lesson.java.queue.messagequeue;

public class Handler {
    private Looper mLooper;

    public Handler() {
        this(Looper.myLooper());
    }

    public Handler(Looper looper) {
        if (looper == null) {
            throw new NullPointerException("looper can't be null.  HandlerThread is running?");
        }
        mLooper = looper;
    }

    public void remove(Message message) {
        mLooper.getMessageQueue().remove(message);
    }

    public void post(Message message) {
        mLooper.getMessageQueue().post(message);
    }

    public void post(Runnable runnable) {
        Message message = new Message();
        message.setRunnable(runnable);
        post(message);
    }
}
