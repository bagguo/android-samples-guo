package com.example.guo.oop.delegation

import kotlin.reflect.KProperty

/**
 * 委托属性允许将属性set和get方法的调用委托给某个对象
 */
class Example {
    var p: String by Delegate()
}

class Delegate() {
    //operator运算符重载
    operator fun getValue(thisRef: Any?, prop: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${prop.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: String) {
        println("$value has bean assigned to ${prop.name} in $thisRef")
    }
}

fun main(){
    val e = Example()
    println(e.p)
    e.p = "NEW"
}