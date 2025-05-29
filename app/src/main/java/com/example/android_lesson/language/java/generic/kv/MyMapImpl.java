package com.example.android_lesson.language.java.generic.kv;

public class MyMapImpl<K, V> implements MyMap<K, V> {
    private final K key;
    private final V value;
    public MyMapImpl(K key, V value){
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey(){ return key; }

    @Override
    public V getValue(){ return value; }
}