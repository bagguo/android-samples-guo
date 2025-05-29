package com.example.guo.language.java.innerclass;

/**
 * 外部类
 * 内部类
 * 匿名内部类
 * 静态内部类(静态嵌套类)
 */
public class OuterClass {

    private int mOuterNum = 0;

    public static void main(String[] args) {

    }


    /**
     * 内部类：
     * <p>
     * 依附于外部类，持有外部类的引用
     * 有OuterClass private属性的访问权
     * <p>
     *
     * 运行javac OuterClass.java
     * 编译输出：OuterClass$Inner.class
     */
    class Inner {
//        OuterClass outer = new OuterClass();

        private void set() {
            mOuterNum = 1;
        }
    }


    /**
     * 匿名内部类
     * <p>
     * 与内部类特点一致
     * <p>
     * 编译输出：OuterClass$1.class
     */
    void asyncHello() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mOuterNum = 2;

                System.out.println("Hello, " + OuterClass.this.getClass().getSimpleName());
            }
        };
        new Thread(runnable).start();
    }


    /**
     * 静态内部类、静态嵌套类(Static Nested Class)：
     * <p>
     * 独立类，不持有外部类的引用
     * 没有OuterClass private属性的访问权
     * <p>
     * 编译输出：OuterClass$StaticInner.class
     */
    static class StaticInner {
        private void test() {
//            mOuterNum = 3; //静态内部类不允许访问外部类的属性
        }

    }
}
