package com.example.android_lesson.java.queue.messagequeue;

import javax.swing.plaf.synth.SynthLookAndFeel;
import java.util.ArrayDeque;
import java.util.Queue;

public class MessageQueue {
    private final Queue<Message> mQueue = new ArrayDeque<>();

    public void post(Message message) {
        synchronized (mQueue) {
            mQueue.notify();
            mQueue.add(message);
        }
    }

    public void remove(Message message) {
        synchronized (mQueue) {
            mQueue.notify();
            mQueue.remove(message);
        }
    }

    public Message next() {
        while (true) {
            synchronized (mQueue) {
                try {
                    if (!mQueue.isEmpty()) {
                        return mQueue.poll();
                    }
                    mQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
