package com.example.android_lesson.language.kotlin.oop.constructor


class Person {
    var name: String
    var age: String? = null

    constructor(name: String) {
        this.name = name
    }

    constructor(name: String, age: String?) {
        this.name = name
        this.age = age
    }
}