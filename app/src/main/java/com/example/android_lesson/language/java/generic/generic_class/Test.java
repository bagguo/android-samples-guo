package com.example.android_lesson.language.java.generic.generic_class;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型类
 * @param <T>
 */
public class Test<T> {
    public List list = new ArrayList();
    public static void main(String[] args){        
        Test test = new Test();
        test.list.add("hello");
        System.out.println(test.list);
    }
}