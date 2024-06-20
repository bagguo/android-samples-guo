package com.example.android_lesson.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

public class Sort2 {

    public static void main(String[] args) {
        bubbleSort();
    }

    // 0...1000中随机取10个不同数字，并从小到大打印
    public static void bubbleSort() {

        //get random num from 0-1000
        List<Integer> list = new ArrayList<>();
        int num;
        for (int i = 0; i < 10; i++) {
            num = (int) (Math.random() * 1000);
            System.out.println("num is: " + num);
            list.add(num);
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(i) < list.get(j)) {
                    int temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }

        //print result
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }

    public void quickSort() {
    }

}
