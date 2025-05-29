package com.example.guo.language.kotlin.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Flow:
 * 一种异步数据流，它按顺序发出值并正常完成或出现异常。
 *
 * 流上的中间操作符（例如 map、filter、take、zip 等）是应用于上游流或多个流并返回下游流的函数，可以在下游流中应用进一步的操作符。中间操作不会在流中执行任何代码，也不会暂停函数本身。它们只设置一个操作链以供将来执行并快速返回。这称为冷流属性。
 * 流上的终端操作符要么是暂停函数（例如 collect、single、reduce、toList 等），要么是启动给定范围内流收集的 launchIn 操作符。它们应用于上游流并触发所有操作的执行。流的执行也称为收集流，并且始终以暂停方式执行，而不会实际阻塞。终端操作符正常或异常完成，具体取决于上游所有流操作的成功或失败执行。最基本的终端操作符是 collect，例如：
 * ```
 * try {
 *         flow.collect { value ->
 *             println("Received $value")
 *         }
 *    } catch (e: Exception) {
 *         println("The flow has thrown an exception: $e")
 *    }
 * ```
 *
 * 默认情况下，流是顺序的，所有流操作都在同一协程中顺序执行，但一些专门设计用于将并发性引入流执行的操作除外，例如缓冲区和 flatMapMerge。有关详细信息，请参阅其文档。
 * Flow 接口不包含以下信息：流是可以重复收集且每次收集时都会触发相同代码执行的冷流，还是每次收集时从同一运行源发出不同值的热流。通常，流代表冷流，但有一个 SharedFlow 子类型代表热流。除此之外，任何流都可以通过 stateIn 和 shareIn 运算符转变为热流，或者通过 produceIn 运算符将流转换为热通道。
 *
 * ### 流构建器
 * 创建流的基本方法如下：
 * flowOf(...) 函数用于从一组固定的值创建流。
 * 各种类型的 asFlow() 扩展函数用于将它们转换为流。
 * flow { ... } 构建器函数用于从对 emit 函数的顺序调用构建任意流。
 * channelFlow { ... } 构建器函数用于从对 send 函数的潜在并发调用构建任意流。
 * MutableStateFlow 和 MutableSharedFlow 定义相应的构造函数函数来创建可以直接更新的热流。
 *
 * ### 流约束
 * Flow 接口的所有实现都必须遵守下面详细描述的两个关键属性：
 * 上下文保存。
 * 异常透明性。
 * 这些属性确保能够使用流对代码进行局部推理，并以这样一种方式模块化代码，即上游流发射器可以与下游流收集器分开开发。流的用户不需要知道它使用的上游流的实现细节。
 *
 * ### 上下文保存
 * 流具有上下文保存属性：它封装了自己的执行上下文，并且永远不会向下游传播或泄漏，因此，推断特定转换或终端操作的执行上下文变得非常简单。
 * 只有一种方法可以更改流的上下文：更改上游上下文的 flowOn 运算符（“flowOn 运算符之上的所有内容”）。有关更多信息，请参阅其文档。
 * 这种推理可以在实践中得到证明：
 *
 * ```
 * val flowA = flowOf(1, 2, 3)
 *     .map { it + 1 } // Will be executed in ctxA
 *     .flowOn(ctxA) // Changes the upstream context: flowOf and map
 *
 * // Now we have a context-preserving flow: it is executed somewhere but this information is encapsulated in the flow itself
 *
 * val filtered = flowA // ctxA is encapsulated in flowA
 *    .filter { it == 3 } // Pure operator without a context yet
 *
 * withContext(Dispatchers.Main) {
 *     // All non-encapsulated operators will be executed in Main: filter and single
 *     val result = filtered.single()
 *     myUi.text = result
 * }
 * ```
 *
 * 从实现的角度来看，这意味着所有流实现都应仅从同一协程发出。此约束由默认流构建器有效执行。如果流实现未启动任何协程，则应使用流构建器。它的实现可以防止大多数开发错误：
 *
 * ```
 * val myFlow = flow {
 *    // GlobalScope.launch { // is prohibited
 *    // launch(Dispatchers.IO) { // is prohibited
 *    // withContext(CoroutineName("myFlow")) { // is prohibited
 *    emit(1) // OK
 *    coroutineScope {
 *        emit(2) // OK -- still the same coroutine
 *    }
 * }
 * ```
 *
 * 如果要将流的收集和发射分成多个协程，请使用 channelFlow。它封装了所有上下文保存
 *
 *
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