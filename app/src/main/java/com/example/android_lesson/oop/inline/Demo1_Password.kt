package com.example.android_lesson.oop.inline

inline class Password(val value: String)

fun main() {
    //test 1
    val securePassword = Password("Don't try in production")


    //test 2
    val name = Name("kotlin")
    name.greet() //'greet' 方法会作为一个静态方法被调用
    println(name.length) //属性的get方法会作为一个静态方法被调用
}

inline class Name(val s: String) {
    val length: Int
        get() = s.length

    fun greet() {
        println("Hello, $s")
    }
}