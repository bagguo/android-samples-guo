# LifecycleOwner

该对象一般为Activity\Fragment

## repeatOnLifecycle

LifecycleOwner的生命周期扩展函数。允许从lifecycleowner(比如Activities和Fragments)更容易地调用API。

```
// 当生命周期至少处于STARTED状态时，在协程中运行代码块。
// 当ON_STOP事件发生时，协程将被取消，如果生命周期再次接收到ON_START事件，协程将重新开始执行
repeatOnLifecycle(
    state: Lifecycle.State,
    block: suspend CoroutineScope.() -> Unit
): Unit = lifecycle.repeatOnLifecycle(state, block)
```