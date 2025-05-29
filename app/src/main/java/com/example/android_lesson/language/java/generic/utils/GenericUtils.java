package com.example.android_lesson.language.java.generic.utils;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Author：Jay On 2019/5/11 21:05
 * <p>
 * Description: 泛型相关的工具类
 */
public class GenericUtils {

    public static class Movie {
        private final String name;
        private final Date time;

        public String getName() {
            return name;
        }

        public Date getTime() {
            return time;
        }

        public Movie(String name, Date time) {
            this.name = name;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Movie{" + "name='" + name + '\'' + ", time=" + time + '}';
        }
    }

    public static void main(String[] args) {
        List<Movie> movieList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            movieList.add(new Movie("movie" + i, new Date()));
        }
        System.out.println("排序前:" + movieList);

         GenericUtils.sortAnyList(movieList, "name", true);
         System.out.println("按name正序排：" + movieList.toString());

         GenericUtils.sortAnyList(movieList, "name", false);
         System.out.println("按name逆序排：" + movieList.toString());
    }

    /**
     * 对任意集合的排序方法
     *
     * @param targetList 要排序的实体类List集合
     * @param sortField  排序字段
     * @param sortMode   true正序，false逆序
     */
    public static <T> void sortAnyList(List<T> targetList, final String sortField, final boolean sortMode) {
        if (targetList == null || targetList.size() < 2 || sortField == null || sortField.length() == 0) {
            return;
        }
        Collections.sort(targetList, new Comparator<Object>() {
            @Override
            public int compare(Object obj1, Object obj2) {
                int retVal = 0;
                try {
                    // 获取getXxx()方法名称
                    String methodStr = "get" + sortField.substring(0, 1).toUpperCase() + sortField.substring(1);
                    Method method1 = ((T) obj1).getClass().getMethod(methodStr, null);
                    Method method2 = ((T) obj2).getClass().getMethod(methodStr, null);
                    if (sortMode) {
                        retVal = method1.invoke(((T) obj1), null).toString().compareTo(method2.invoke(((T) obj2), null).toString());
                    } else {
                        retVal = method2.invoke(((T) obj2), null).toString().compareTo(method1.invoke(((T) obj1), null).toString());
                    }
                } catch (Exception e) {
                    System.out.println("List<" + ((T) obj1).getClass().getName() + ">排序异常！");
                    e.printStackTrace();
                }
                return retVal;
            }
        });
    }
}