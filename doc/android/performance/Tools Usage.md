## 工具选择

AS Profiler：

* 页面已打开，看卡顿、锁、掉帧用
* 启动真机可能会丢掉bindApplication，模拟器没有丢

命令行：

* 冷启动、bindApplication、类加载
* 同一路径录两次做对比

## Profiler

### System Trace

#### 放大缩小

如果没反应，鼠标点击左侧区块条目，表示选中

* Y轴放大缩小：W/S, Command + 鼠标滚轮/- +
* Y轴左右移动：A/D

####

System Trace 顶部那条状态轨看的是 Linux 调度状态