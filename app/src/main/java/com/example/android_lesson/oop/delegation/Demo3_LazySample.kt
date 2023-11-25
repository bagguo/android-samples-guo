package com.example.android_lesson.oop.delegation

/**
 * Kotlin 标准库包含许多有用的委托，例如lazy、observable等
 */
class LazySample {
    init {
        println("created")
    }

    /**
     * 想要线程安全，请改用blockingLazy()
     */
    //lazy用于延迟初始化
    val lazyStr: String by lazy {
        println("computed!")
        "my lazy"
    }
}

fun main() {
    val sample = LazySample()
    println("lazyStr = ${sample.lazyStr}")
    println(" = ${sample.lazyStr}")
}