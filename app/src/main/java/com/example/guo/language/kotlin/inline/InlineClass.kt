package com.example.guo.language.kotlin.inline

/**
 * class Password{
 *     private String password;
 *     public Password(String p){
 *         this.password = p
 *     }
 * }
 * 缺点：new Password()需要单独创建实例，放在heap
 */

/**
 * inline class: 简化java bean，占用内存少
 */
@JvmInline
value class Password(val value: String)

@JvmInline
value class Name(val s: String) {
    val length: Int
        get() = s.length

    fun greet() {
        println("Hello, $s")
    }
}

fun main() {
    //test 1
    val securePassword = Password("Don't try in production")


    //test 2
    val name = Name("kotlin")
    name.greet() //'greet' 方法会作为一个静态方法被调用
    println(name.length) //属性的get方法会作为一个静态方法被调用
}

