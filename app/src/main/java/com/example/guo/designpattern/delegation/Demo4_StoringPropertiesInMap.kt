@file:Suppress("ktlint:standard:filename")

package com.example.guo.designpattern.delegation

/**
 * 用委托在map存储属性
 */
class User(
    map: Map<String, Any?>,
) {
    val name: String by map
    val age: Int by map
}

fun main() {
    val user =
        User(
            mapOf(
                "name" to "John Doe",
                "age" to 25,
            ),
        )

    println("name = ${user.name}, age = ${user.age}")
}
