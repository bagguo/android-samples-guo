package com.example.android_lesson.language.java.generic.kv;

public class Test {

    public static void main(String[] args) {
        MyMap<String, Integer> mp1= new MyMapImpl<String, Integer>("Even", 8);
        MyMap<String, String> mp2= new MyMapImpl<String, String>("hello", "world");
        MyMap<Integer, Integer> mp3= new MyMapImpl<Integer, Integer>(888, 888);

        System.out.println(mp1.getKey());
        System.out.println(mp2.getValue());
        System.out.println(mp3.getKey());

    }
    
    
    

    

    // public void print(T1 t1,T2 t2,T3 t3){        
    //     System.out.println(t1.getClass());        
    //     System.out.println(t2.getClass());        
    //     System.out.println(t3.getClass());    
    // }
}