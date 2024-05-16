package com.example.android_lesson.algorithm.sort

fun main() {
    bubbleSort()
}

/**
 * 0...1000中随机取10个不同数字，并从小到大打印
 */

/**
 * 冒泡排序
 */
fun bubbleSort() {
    val nums = mutableListOf<Int>()

    // get 10 random num from 0...1000
    println("random: ")
    var element: Int
    for (i in 0..9) {
        element = (0..1000).random()
        nums.add(i, element)
        print("$element ")
    }


    var temp: Int
    for (i in 0 until nums.size) {
        for (j in 0 until nums.size) {
            //交换值
            if (nums[i] < nums[j]) {
                temp = nums[i]
                nums[i] = nums[j]
                nums[j] = temp
            }
        }
    }

    println("\nresult:")
    nums.forEach {
        println(it)
    }
}