package com.example.android_lesson.language.kotlin.list

/**
 * @title
 * @author Darren.eth
 */

fun main() {
    sort()
}

fun sort() {
    val list = mutableListOf("0", "1", "d", "b", "a", "c", "3")

    // 按名称排序时数字在前，字母在后
    list.sortBy { it }

    // 输出：
    // 0
    //1
    //3
    //a
    //b
    //c
    //d
    list.forEach {
        println(it)
    }
}