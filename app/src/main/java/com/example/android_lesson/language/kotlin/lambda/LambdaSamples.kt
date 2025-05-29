package com.example.android_lesson.language.kotlin.lambda

/**
 * @title lambda
 * @author Darren.eth
 * @Date
 *
 * () -> : 函数类型，描述参数和返回值的类型，常见于高阶函数和lambda中
 * () -> Type :表示一个不接受参数且返回Type类型值的函数
 * (Param1Type, Param2Type) -> ReturnType :表示一个接收参数并返回ReturnType类型值的函数
 */
fun main() {

    LambdaSamples().apply {
        doOperation {
            println("Operation executed")
        }
        doOperation(println("Operation executed"))


        println(sum(1, 2))

        performOperation(10, 20, sum)
        performOperation(10, 20) { a, b ->
            a + b
        }
        performOperation(10, 20) { a, b ->
            a * b
        }

        //
        val multiplier = createMultiplier(3)
        println(multiplier(5)) //输出15

        println(performOperation()) //输出10
        println(performOperation { it + 3 }) //输出8

        functionWithOperationalParams(5, 10)
        functionWithOperationalParams(null, null)
    }


}

class LambdaSamples {

    // 在lambda或高阶函数中，返回Unit意味着没有返回有意义的值
    val printSomething: () -> Unit = {
        println("Hello World")
    }

    // 简化代码
    val printSomething2 = {
        println("Hello World")
    }

    // 高阶函数可以接收返回Unit的函数作为参数
    fun doOperation(action: () -> Unit) {
        action()
    }

    fun doOperation(action: Unit) {
        return action
    }

    // 描述参数和返回值的类型
    val sum: (Int, Int) -> Int = { a, b ->
        println("start sum a + b:")
        a + b
    }

    // 高阶函数使用()->语法
    // 接收两个Int参数和一个(Int, Int)->Int类型的函数作为参数
    fun performOperation(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
        return operation(a, b)
    }

    // 返回函数的高阶函数
    fun createMultiplier(factor: Int): (Int) -> Int {
        // 返回一个函数(Int) -> Int，该函数接收一个整数并将它乘以给定的factor
        return { number -> number * factor }
    }

    // 传带默认参数的函数类型
    fun performOperation(operation: (Int) -> Int = { it * 2 }): Int {
        return operation(5)
    }

    // （）-> 参数的可选性
    val functionWithOperationalParams: (Int?, Int?) -> Unit = { a, b ->
        println("result: $a $b")
    }
}