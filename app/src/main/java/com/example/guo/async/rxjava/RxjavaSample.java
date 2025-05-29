package com.example.guo.async.rxjava;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RxjavaSample {
    private static final String TAG = RxjavaSample.class.getName();

    public void helloWorld() {
        Flowable.just("Hello world").subscribe(System.out::println);
    }

    Disposable mDisposable;

    /**
     * 方式2: 链式调用
     */
    void chainCall() {
        //整体方法调用顺序：观察者.onSubscribe（）> 被观察者.subscribe（）> 观察者.onNext（）>观察者.onComplete()
        //ObservableOnSubscribe 被观察者被订阅
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "subscribe: ");
                emitter.onNext(1);
                Thread.sleep(1000);
                emitter.onNext(2);
                Thread.sleep(1000);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
                mDisposable = d;
            }

            @Override
            public void onNext(Integer v) {
                Log.d(TAG, "onNext:  对Next事件" + v + "作出响应");
                if (v == 2) {
                    // 设置在接收到第二个事件后切断观察者和被观察者的连接
                    mDisposable.dispose();
                    Log.d(TAG, " 已经切断了连接：" + mDisposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });
    }

    /**
     * 实现简便式的观察者模式
     */
    void simpleObserve() {
        Log.d(TAG, "simpleObserve: ");
        Observable.just("hello").subscribe(new Consumer<String>() {
            //每次接收到Observable的事件都会调用Consumer.accept()
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "accept: " + s);
            }
        });
    }

    /**
     * 方式1: 分步骤创建
     */
    void test() {
        /**
         * 步骤1 创建被观察者 & 生产事件
         */
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            // create()是 RxJava 最基本的创建事件序列的方法
            // 此处传入了一个 OnSubscribe 对象参数
            // 当 Observable 被订阅时，OnSubscribe 的 call()方法会被自动调用，即事件序列就会依照设定依次被触发
            // 即观察者会依次调用对应事件的覆写方法，从而响应事件
            // 从而实现被观察者调用了观察者的方法 & 由被观察者向观察者传递事件，即观察者模式
            // 2.在覆写的subscribe() 里定义需要发送的事件
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "test subscribe: ");
                // 通过ObservableEmitter产生事件并通知观察者
                // ObservableEmitter： 被观察者事件发射器
                // 1.定义需要发送的事件
                // 2.向观察者发送事件
                emitter.onNext(1); //发射器，发射体
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });

        // 创建方式2, just直接将传入的参数发送出来
        Observable.just("A", "B", "C");
        // 将会依次调用
        //onNext("A");
        //onNext("B");
        //onNext("C");
        // onCompleted();

        //创建方式3 from(T[]) / from(Iterable<? extends T>):将传入的数组、Iterable依次发送出来
        String[] words = {"A", "B", "C"};
        Observable.fromArray(words);
        // 将会依次调用
        //onNext("A");
        //onNext("B");
        //onNext("C");
        // onCompleted();

        /**
         * 步骤2，创建观察者Observer 并 定义响应事件的行为
         * 即开厨房-确定对应菜式
         * 发生的事件类型包括：Next 事件，Complete 事件，Error 事件
         */
        // 方式1，采用Observer接口
        // 1.创建观察者对象
        Observer<Integer> observer = new Observer<Integer>() {
            //响应事件的接口
            //观察者接收事件前，默认最先调用onSubscribe()
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: 开始采用subscribe连接");
            }

            // 当被观察者生产事件，观察者接收到时回调
            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: 对Next事件作出响应：" + integer);
            }

            // 当被观察者生产error事件
            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: 对Error事件作出响应");
            }

            //当被观察者生产Complete事件
            @Override
            public void onComplete() {
                Log.d(TAG, "test onComplete: ");
            }
        };

        //方式2，采用Subscribe抽象类
        // Subscriber = RxJava 内置的一个实现了Observer的抽象类，对Observer接口进行了扩展
        //1. 创建观察者 （Observer ）对象
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            // 2. 实现方法 响应对应事件
            // 观察者接收事件前，默认最先调用复写 onSubscribe（）
            @Override
            public void onSubscribe(Subscription s) {
                Log.d(TAG, "onSubscribe: =");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: ");
            }

            @Override
            public void onError(Throwable t) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }

        };

        /**
         * 步骤3，subscribe连接观察者和被观察者
         */
        observable.subscribe(observer);
        //或者
//        observable.subscribe((Consumer<? super Integer>) subscriber);
    }


}
