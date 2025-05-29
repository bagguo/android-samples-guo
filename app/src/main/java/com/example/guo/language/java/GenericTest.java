package com.example.guo.language.java;

import java.util.ArrayList;
import java.util.HashMap;  
import java.util.List;

//T1,T2都是随便定义的东西，注意1:他们不会关联到其他类，只是在本类中通用，只是告诉我们new的时候要加入泛型
public class GenericTest {  
    public static void main(String[] args) {  
//        System.out.println(new GenericTest().getaa());
        // new GenericTest().getbb("");  
        new GenericTest().getcc(GenericTest.class);  
        //注意下6:面这个HashMap的括号里面不能是T,E,T1,T2等不确定的东西,但可以是?
        HashMap map = new HashMap();  
        List list = new ArrayList();  
    }  

//    T2 getaa() {
//        //注意2:T2将自动转型为String,这个不需要去担心
//        return (T2) "few";
//    }

    // public  void getbb(T x) {  
    //     //注意3:Class前面缺少将编译错误
    //     System.out.println(x.getClass().getName());  
    // } 

    public Class getcc(Class a) {  
            //getcc前面的Class前面缺少将编译错误,注意4:Class里面的问号可以换成T
            System.out.println("=====" + a.getClass().getName());  
            //注意5:参数里面的Class最大的好处是如果方法里面定义了泛型，可以自动获取类型值，比如如下的List可以自动获取到a的类型，不必强调死
            List aa=new ArrayList();  
            System.out.println(aa);  
            return a;  
        }  
}  