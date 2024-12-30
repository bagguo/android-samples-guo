package com.example.android_lesson.kotlin.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Flow:
 * flow()：创建一个Flow实例
 *
 * emit()：发射
 * collect()：收集, emit一个值，collect收到该值
 *
 * combine()：组合
 */

fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        // 发送
        emit(i)
    }
}


fun main() = runBlocking<Unit> {
    launch {
        for (k in 1..3) {
            println("I‘m not blocked $k")
            delay(100)
        }
    }


    // 收集
    simple().collect { value -> println(value) }

    //combine
    // 返回一个 Flow，其值由转换函数通过组合每个 flow 最近发出的值生成。
    //可以用以下示例进行演示：
    val flow = flowOf(1, 2).onEach {
        delay(10)
    }
    val flow2 = flowOf("a", "b", "c").onEach {
        delay(15)
    }

    flow.combine(flow2) { i, s ->
        i.toString() + s
    }.collect {
        println(it) // Will print "1a 2a 2b 2c"
    }
}