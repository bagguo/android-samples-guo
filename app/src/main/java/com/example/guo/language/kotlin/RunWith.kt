package com.example.guo.language.kotlin

class RunWith {

}

fun main() {

    /**
     * with inline函数，把传入的对象作为接受者，在该函数内可使用this指代该对象
     */
    val person: Person = Person("hzh", 23)
    val result = with(person) {
        age = 24
        eat()
        work(8) //返回480
    }
    println("result is:$result")
}

class Person(var name: String, var age: Int) {

    fun eat() {
        println("吃柠檬")
    }

    fun work(hour: Int): Int {
        println("work $hour , earn ¥${hour * 60}")
        return hour * 60
    }


}