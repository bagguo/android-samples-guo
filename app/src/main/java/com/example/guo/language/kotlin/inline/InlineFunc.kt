package com.example.guo.language.kotlin.inline

/**
 * inline函数: 把函数体复制粘贴到函数调用处
 * 使用场景：函数入参
 */
inline fun test() {
    println("I'm a inline function")
}

fun main() {
    run(test())
}

fun run(block: Unit) {
    block.run {

    }
}
