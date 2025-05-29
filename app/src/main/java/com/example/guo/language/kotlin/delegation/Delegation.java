package com.example.guo.language.kotlin.delegation;

/**
 * 委托：把自己要做的事委托给别人做
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

//demo 2 复杂的Java例子

interface I {
    void f();
    void g();
}

class A implements I {
    public void f() { System.out.println("A: doing f()"); }
    public void g() { System.out.println("A: doing g()"); }
}

class B implements I {
    public void f() { System.out.println("B: doing f()"); }
    public void g() { System.out.println("B: doing g()"); }
}

class C implements I {
    // delegation
    I i = new A();

    public void f() { i.f(); }
    public void g() { i.g(); }

    // normal attributes
    public void toA() { i = new A(); }
    public void toB() { i = new B(); }
}


 class DelegationDemo2 {
    public static void main(String[] args) {
        C c = new C();
        c.f();     // output: A: doing f()
        c.g();     // output: A: doing g()
        c.toB();
        c.f();     // output: B: doing f()
        c.g();     // output: B: doing g()
    }
}