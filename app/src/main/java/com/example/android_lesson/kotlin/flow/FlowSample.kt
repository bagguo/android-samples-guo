package com.example.android_lesson.kotlin.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FlowSample {

}

fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
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


    simple().collect { value -> println(value) }
}