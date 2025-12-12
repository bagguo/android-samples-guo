package com.example.guo.algorithm.map

import android.util.SparseArray



fun main() {
    use()
}

fun use() {
    val map = SparseArray<String>()
    map.put(0, "Tom")
    map.put(1, "Bob")
    val first = map.get(0)
    println(first)
}

