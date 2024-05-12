package com.example.android_lesson.java.synchronised;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class BaeldungSynchronizedMethods {

    public static void main(String[] args) {
        givenMultiThread_whenNonSyncMethod();
    }

    private int sum = 0;

    public synchronized void calculate() {
        setSum(getSum() + 1);

    }

    public void setSum(int v) {
        sum = v;
    }

    public int getSum() {
        return sum;
    }

    public static void givenMultiThread_whenNonSyncMethod() {
        ExecutorService service = Executors.newFixedThreadPool(3);

        BaeldungSynchronizedMethods summation = new BaeldungSynchronizedMethods();

        IntStream.range(0, 1000).forEach(count -> service.submit(summation::calculate));
        try {
            service.awaitTermination(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
        assertEquals(1000, summation.getSum());
    }

    private static void assertEquals(int i, int sum2) {
        System.out.println(i);
        System.out.println(sum2);
        System.out.println("=====");
    }
}