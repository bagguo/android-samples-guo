package com.example.guo.designpattern.delegation;

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
