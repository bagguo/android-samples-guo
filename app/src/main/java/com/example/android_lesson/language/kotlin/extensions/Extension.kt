package com.example.android_lesson.language.kotlin.extensions

class Extension {

}

fun main() {
    "adb".hello("hello world")
    mutableListOf<Int>(99, 100).swap(0, 1)
}

/**
 * 扩展函数的本质是在该类里生成static method
 */
fun String.hello(hello: String) {
    println(hello)
}

fun MutableList<Int>.swap(index1: Int, index2: Int) {
    println("original data: index1: ${this[index1]}, index2: ${this[index2]}")
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
    println("after swap: index1: ${this[index1]}, index2: ${this[index2]}")
}


