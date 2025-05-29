package com.example.android_lesson.language.java.queue.messagequeue;

public class Message {

    private Runnable mRunnable;
    private int arg;

    public Message(Runnable runnable) {
        mRunnable = runnable;
    }

    public Message(int arg) {
        this.arg = arg;
    }

    public Message(Runnable runnable, int arg) {
        mRunnable = runnable;
        this.arg = arg;
    }

    public Message() {
    }

    public Runnable getRunnable() {
        return mRunnable;
    }

    public void setRunnable(Runnable runnable) {
        mRunnable = runnable;
    }

    public int getArg() {
        return arg;
    }

    public void setArg(int arg) {
        this.arg = arg;
    }
}
