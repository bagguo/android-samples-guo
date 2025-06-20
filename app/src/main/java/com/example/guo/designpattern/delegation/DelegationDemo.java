package com.example.guo.designpattern.delegation;

/**
 * 委托：
 * 把自己要做的事委托给别人做，delegator委托者 委托 delegate被委托者 做什么，使用别人的实现。
 * 委托模式使得我们可以用聚合替代继承。
 * <a href="https://zh.wikipedia.org/wiki/%E5%A7%94%E6%89%98%E6%A8%A1%E5%BC%8F">来源</a>
 */
class RealPrinter { //the "delegate" 被委托者
    void print() {
        System.out.print("something");
    }
}

class Printer { //the "delegator" 委托者
    RealPrinter p = new RealPrinter();

    void print() {
        p.print();// delegation
    }
}

class DelegationDemo {

    public static void main(String[] args) {
        Printer printer = new Printer();
        printer.print();
    }
}