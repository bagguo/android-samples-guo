Todo:
1.applicationId 和包名不同时androidx.startup.InitializationProvider 找不到

2.对于 API 级别较高的非可折叠手机，是否支持 activity 嵌入取决于设备制造商是否支持 activity 嵌入。

非折叠手机都不需要分屏

特性：
手机：

在 Android 13（API 级别 33）及更低版本中，无论分屏最小宽度规范如何，非可折叠手机上都不支持 activity 嵌入。

对于 API 级别较高的非可折叠手机，是否支持 activity 嵌入取决于设备制造商是否支持 activity 嵌入。

pad:

在小屏平板电脑或 7 WSVGA（平板电脑）模拟器上，两个 activity 在纵向模式下会堆叠显示，而在横向模式下则会并排显示

在大屏平板电脑或 Pixel C 模拟器上，activity 在纵向模式下会堆叠显示，而在横向模式下则会并排显示