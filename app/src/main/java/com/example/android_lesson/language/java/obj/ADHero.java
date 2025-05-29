package com.example.android_lesson.language.java.obj;

public class ADHero extends Hero implements AD{
  
    @Override
    public void physicAttack() {
        System.out.println("进行物理攻击");
    }
     
    //隐藏父类的battleWin方法
    public static void battleWin(){
        System.out.println("ad hero battle win");
    }   
     
    public static void main(String[] args) {
        Hero.battleWin();
        ADHero.battleWin();
    }
  
}