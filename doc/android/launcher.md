<!-- TOC -->

* [作用](#作用)
* [Launcher启动过程](#launcher启动过程)
* [Launcher中应用图标的显示过程](#launcher中应用图标的显示过程)
* [参考](#参考)

<!-- TOC -->

# 作用

1. Launcher是Android系统的启动器，启动其他应用程序
2. 显示和管理应用程序的快捷图标和其他桌面组件

# Launcher启动过程

SystemServer启动PMS
AMS启动Launcher

```
SystemServer{
    startOtherServices() {
        mAMS.systemReady() 
    }
}
```

# Launcher中应用图标的显示过程

# 参考

[Android系统启动流程之Launcher进程启动](https://jsonchao.github.io/2019/03/09/Android%E7%B3%BB%E7%BB%9F%E5%90%AF%E5%8A%A8%E6%B5%81%E7%A8%8B%E4%B9%8BLauncher%E8%BF%9B%E7%A8%8B%E5%90%AF%E5%8A%A8/)