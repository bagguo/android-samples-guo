# Start up optimize

## use AS Profiler System Trace catch trace

1. 捕获周期：从应用启动到，首页完全加载出来
2. 在Threads条目选中main thread(包名同名的thread, tid等于该进程pid)，选中的线程条目最顶部是thread运行状态：
   Running, Sleeping, Waiting, Runnable, No thread activity
3. 用wsad放大缩小、左右移动查看slice

### 看主线程

1. 先框住 主线程从出现 到 第一帧 Choreographer#doFrame ，再按状态判断时间是花在自己算、等CPU、等待锁/Binder、等磁盘
2. bindApplication，冷启动里通常最长的一段。展开看:
    * ContentProvider#onCreate() // 很多 SDK 藏在这里
    * Application.attachBaseContent / onCreate
    * 类初始化，名字是 L包名/类名。前面的 L 是 JVM 类型描述符，表示这是一个类加载/初始化
3.

com.example.guo 启动 slice

```
bindApplication
  |- Lcom/example/guo/LeakCanarySetup
     SplitManager
     SplitManager$companion
     SplitPairFilter
     SplitListActivity
     embedding/DetailActivity
     Landroidx/core/window/ActivityComponentInfo
     Landroidx/window/embedding/SplitAttributes$Builder

// 类名前的 L 是JVM类描述符里表示 “这是一个引用类型” 的前缀，如Lcom/example/guo/LeakCanarySetup
```