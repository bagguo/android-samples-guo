package com.example.android_lesson.algorithm.string

/**
 * @title
 * @author Darren.eth
 * @Date
 */

val s = "\"ox22d9\""

fun main() {
    println(removeExtraQuotes(s))


    /**
     * when语句可以传null
     */
    val a: Int? = null
    when (a) {
        1 -> {
            println(1)

        }
        null->{
            println(null)
        }
    }
}

/**
 * 删除字符串中首末多余的双引号
 */
fun removeExtraQuotes(input: String): String {
    return input.substring(1, input.length - 2)
}