规范
=========

<!-- TOC -->

- [规范](#%E8%A7%84%E8%8C%83)
- [概述](#%E6%A6%82%E8%BF%B0)
- [AndroidStudio环境部署](#androidstudio%E7%8E%AF%E5%A2%83%E9%83%A8%E7%BD%B2)
- [编程规范](#%E7%BC%96%E7%A8%8B%E8%A7%84%E8%8C%83)
  - [资源使用规范](#%E8%B5%84%E6%BA%90%E4%BD%BF%E7%94%A8%E8%A7%84%E8%8C%83)
    - [drawable](#drawable)
    - [layout](#layout)
      - [layout控件](#layout%E6%8E%A7%E4%BB%B6)
      - [layout中id的命名](#layout%E4%B8%ADid%E7%9A%84%E5%91%BD%E5%90%8D)
    - [animation](#animation)
    - [color](#color)
    - [strings](#strings)
    - [其他](#%E5%85%B6%E4%BB%96)
  - [基本组件](#%E5%9F%BA%E6%9C%AC%E7%BB%84%E4%BB%B6)
    - [Activity](#activity)
    - [Fragment](#fragment)
    - [Broadcast](#broadcast)
  - [布局](#%E5%B8%83%E5%B1%80)
  - [线程、进程、通信](#%E7%BA%BF%E7%A8%8B%E8%BF%9B%E7%A8%8B%E9%80%9A%E4%BF%A1)
  - [文件与数据库](#%E6%96%87%E4%BB%B6%E4%B8%8E%E6%95%B0%E6%8D%AE%E5%BA%93)
  - [Bitmap和Drawable对象](#bitmap%E5%92%8Cdrawable%E5%AF%B9%E8%B1%A1)
  - [安全](#%E5%AE%89%E5%85%A8)
  - [其他](#%E5%85%B6%E4%BB%96)

<!-- /TOC -->



# 概述
- 简要说明规范，保证工程代码一致性

# AndroidStudio环境部署
1. android-sdk很大，修改sdk目录到D盘，位置如下：
```
File > Settings > Appearance & Behavior > System Settings > Android SDK
```

2. 在虚拟机设置项（Help > Edit Custom VM Options）中输入以下内容：
```
#UTF防止windows上乱码
-Dfile.encoding=UTF-8

#设置gradle缓存目录位置，gradle缓存很占空间，最好放到D盘
-Dgradle.user.home=D:/android/.gradle
```
配置IDE可以参考：
[配置IDE](https://developer.android.com/studio/intro/studio-config?hl=zh-cn)

其中，配置gradle环境有4种方式，（优先级从上到下依次降低）：


- 命令行参数（VM Options实际控制的是这个）
- 系统变量（gradle.properties中定义的系统变量）
- gradle变量（在工程根目录或者GRADLE_USER_HOME下的gradle.properties文件中定义的变量）
- 环境变量（操作系统的环境变量）

具体可以参考：
[Configuring the Build Environment](https://docs.gradle.org/5.4.1/userguide/build_environment.html)

3. android-studio配置位于（Help > Edit Custom properties），占用空间很小，可以不配置

# 编程规范

## 资源使用规范

### drawable
- 所有资源都放在统一的资源module下，如果分散在不同module下，需要以module作为资源名前缀，防止资源冲突
- svg-drawable的使用和普通drawable的使用略有不同，使用svg前缀命名，被selector包裹的svg不受影响
- svg一般不应该作为背景使用

文件命名：
- 图标：以ic为前缀，如：ic_video_record_button
- 背景：以bg为前缀，如：bg_main_page_list_view、bg_send_button
- 图标selector：以ic为前缀、selector为后缀，如：ic_video_record_button_selector
- 背景selector：以bg为前缀、selector为后缀，如：bg_main_page_list_view_selector
- svg图标：以svg为前缀，如svg_logo
- svg-selector：同图标selector，如：ic_video_record_button_selector
- 颜色selector：以color_selector为后缀，如commit_button_text_color_selector（注意：color-selector作背景应该归类为背景selector）

> 尽量使用svg
>
> 图片要放在正确的目录下。大图放低分辨率目录可能导致OOM

### layout
文件命名：
- activity以activity做前缀，如：activity_main
- fragment以fragment为前缀，如：fragment_message
- 各种layout以layout为前缀，如：layout_voice_input
- 列表项以item为前缀，如：item_conversation_page_chat_list
- 对话框以dialog为前缀，如：dialog_input_remark
- 弹窗以window为前缀，如：window_more_actions

#### layout控件
定一个类如MyLayout继承自ConstraintLayout，把布局inflate到MyLayout的子级别，MyLayout就变成了一个控件。
> 如果layout顶级是ConstraintLayout，而被inflate的类如果也继承自ConstraintLayout，就凭空多出一个层级而降低了性能。使用merge+parentTag进行优化：
```
<?xml version="1.0" encoding="utf-8"?>
<merge xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:background="@drawable/bg_open_notification_prompt"
    tools:parentTag="androidx.constraintlayout.widget.ConstraintLayout">
```

#### layout中id的命名
- button：btn
- ImageView：iv
- RecyclerView：rv
- TextView：tv
- RadioButton：tb
- ComboBox：cb
- ConstraintLayout：cl
- RelativeLayout：rl （被约束布局卷没了）
- FrameLayout：fl

> 注意：所有layout都应该可以预览

### animation
- 动画以anim为前缀，如anim_side_slide_in、如anim_side_slide_out

### color
所有module的颜色都定义在一个专门资源module在colors.xml
颜色分为：
- 调色盘颜色：色值和名称一一对应，用到哪些颜色一目了然，调色盘颜色不应该直接使用
- 普通颜色：间接地引用【调色盘颜色】，相同的颜色可能含义不同

每种【调色盘颜色】都定义在了全局attr中，布局中颜色使用attr，无法满足要求再使用【普通颜色】。

使用颜色：
```
<TextView
android:id="@+id/tv_text"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintTop_toTopOf="parent"
android:textColor="?attr/mimoColorWhite"
tools:textColor="@color/white"
android:textSize="16sp"
tools:text="文本消息文本消息文本消息文本消息文本消息文本消息"/>
```
上述代码中字色使用了颜色。textColor使用了全局颜色属性mimoColorWhite方便换肤，而为了能够正常显示加入了tools颜色。

### strings
- 布局和代码中禁止使用写死的字符串。
- 所有文字都定义在统一的一个module中。
- 文字在某语言目录不能有缺失。

### 其他
其他资源文件尽量放在统一的资源module中，否则应该以module为前缀防止冲突

## 基本组件

### Activity

- Activity入口参数使用Parcel而不是Serialize，防止IO过多影响性能；Intent避免传递过大数据
- 隐式跳转时使用resolveActivity检查是否可达，防止崩溃
- 尽量不要export组件，避免安全风险
- 避免在onDestroy做释放操作，因为onDestroy可能较晚。使用isFinishing+onPause()/onStop()
- 不要在onPause做耗时操作，因为onPause在onCreate前执行，影响页面跳转效率
- 页面间使用startActivityForResult传Activity需要的数据，而不是把数据放在外部或全局变量里

### Fragment
- FragmentTransaction#commit() 在 Activity#onPostResume()或者 FragmentActivity#onResumeFragments()内调用， commitAllowingStateLoss需要确保没有负面影响
- 使用setArgument传递Fragment需要的数据，而不是把数据放在外部或全局变量

### Broadcast
- 广播注册反注册成对出现
- 避免在广播接收时做耗时操作
- 使用本地广播代替广播

## 布局
- 尽量不增加布局层级
- 不再使用相对布局
- 使用merge、viewStub优化布局
- onDraw方法中不要重复创建对象，防止GC发生卡顿
- 尽量使用DialogFragment，方便管理生命周期
- 使用UTF8
- 避免在子线程绘制UI
- 文字的尺寸使用wrap_content，避免显示不全
- 避免多层布局使用相同背景导致过渡绘制
- 尽量不依赖子View的尺寸，防止子View尺寸变化扩大UI刷新范围
- 不能在 Activity 没有完全显示时显示 PopupWindow 和 Dialog
- 尽量避免使用帧动画，占内存易发OOM
- 禁止ScrollView内嵌列表，导致列表全部显示，违背设计意图且耗内存巨大

## 线程、进程、通信
- 不要通过 Intent 在 Android 基础组件之间传递大数据（binder transaction缓存为 1MB），可能导致 OOM
- 不应该直接创建线程；线程池应该统一管理；线程池必须要给线程命名
- 使用ThreadPoolExecutor而不是Executors创建线程，防止资源耗尽
- ThreadPoolExecutor 设置线程存活时间(setKeepAliveTime)，确保空闲时线程能被释放
- 避免在主线程做IO操作
- GlobalScope没有绑生命周期，注意别内存泄露
- ViewModel不应该包含context

## 文件与数据库
- 不要硬编码文件路径，请使用 Android 文件系统 API 访问
- 当使用外部存储时，必须检查外部存储的可用性
- 应用间共享文件时，不要通过放宽文件系统权限的方式去实现，而应使用FileProvider
- SharedPreference 中只能存储简单数据类型（int、boolean、String 等），复杂数据类型建议使用文件、数据库等其他方式存储
- 数据库 Cursor 必须确保使用完后关闭，以免内存泄漏
- 多线程操作写入数据库时，需要使用事务，以免出现同步问题。
  说明：
  Android 的通过 SQLiteOpenHelper 获取数据库 SQLiteDatabase 实例，Helper 中会
  自动缓存已经打开的 SQLiteDatabase 实例，单个 App 中应使用 SQLiteOpenHelper
  的单例模式确保数据库连接唯一。由于 SQLite 自身是数据库级锁，单个数据库操作
  是保证线程安全的（不能同时写入），transaction 时一次原子操作，因此处于事务中
  的操作是线程安全的。
  若同时打开多个数据库连接，并通过多线程写入数据库，会导致数据库异常，提示
  数据库已被锁住。
- 大数据写入数据库时，请使用事务或其他能够提高 I/O 效率的机制，保证执行速度
- 执行 SQL 语句时，应使用 SQLiteDatabase#insert()、update()、delete()，不要使用 SQLiteDatabase#execSQL()，以免 SQL 注入风险
- 如果 ContentProvider 管理的数据存储在 SQL 数据库中，应该避免将不受信任的外部数据直接拼接在原始 SQL 语句中，可使用一个用于将 ? 作为可替换参 数的选择子句以及一个单独的选择参数数组，会避免 SQL 注入

## Bitmap和Drawable对象
- 加载大图片或者一次性加载多张图片，应该在异步线程中进行。图片的加载，涉及到 IO 操作，以及 CPU 密集操作，很可能引起卡顿
- 在 ListView，ViewPager，RecyclerView，GirdView 等组件中使用图片时，应做好图片的缓存，避免始终持有图片导致内存泄露，也避免重复创建图片，引起性能问题 。 建议使用Fresco、Glide等图片库
- png 图片使用 tinypng 或者类似工具压缩处理，减少包体积
- 应根据实际展示需要，压缩图片，而不是直接显示原图。手机屏幕比较小，直接显示原图，并不会增加视觉上的收益，但是却会耗费大量宝贵的内存
- 在 Activity.onPause()或 Activity.onStop()回调中，关闭当前 activity 正在执行的的动画
- 使用 inBitmap 重复利用内存空间，避免重复开辟新内存
- 使用 ARGB_565 代替 ARGB_888，在不怎么降低视觉效果的前提下，减少内存占用
- 尽量减少 Bitmap（BitmapDrawable）的使用，尽量使用纯色（ColorDrawable）、 渐变色（GradientDrawable）、StateSelector（StateListDrawable）等与 Shape 结 合的形式构建绘图
- 在有强依赖 onAnimationEnd 回调的交互时，如动画播放完毕才能操作页面 ， onAnimationEnd 可 能 会 因 各 种 异 常 没 被 回 调 ， 建 议 加 上 超 时 保 护 或 通 过 postDelay 替 代
  onAnimationEnd

## 安全
- 使用 PendingIntent 时，禁止使用空 intent，同时禁止使用隐式 Intent
  说明：
  使用 PendingIntent 时，使用了空 Intent,会导致恶意用户劫持修改 Intent 的内
  容。禁止使用一个空 Intent 去构造 PendingIntent，构造 PendingIntent 的 Intent
  一定要设置 ComponentName 或者 action。
  PendingIntent 可以让其他 APP 中的代码像是运行自己 APP 中。PendingIntent
  的intent接收方在使用该intent时与发送方有相同的权限。在使用PendingIntent
  时，PendingIntent 中包装的 intent 如果是隐式的 Intent，容易遭到劫持，导致
  信息泄露。
- 禁止使用常量初始化矢量参数构建 IvParameterSpec，建议 IV 通过随机方式产生。
  说明：
  使用固定初始化向量，结果密码文本可预测性会高得多，容易受到字典式攻击。iv
  的作用主要是用于产生密文的第一个 block，以使最终生成的密文产生差异（明文相
  同的情况下），使密码攻击变得更为困难，除此之外 iv 并无其它用途。因此 iv 通过
  随机方式产生是一种十分简便、有效的途径。
- 将 android:allowbackup 属性设置为 false，防止 adb backup 导出数据
- 在实现的 HostnameVerifier 子类中，需要使用 verify 函数效验服务器主机名的合法性，否则会导致恶意程序利用中间人攻击绕过主机名效验。
  说明：
  在握手期间，如果 URL 的主机名和服务器的标识主机名不匹配，则验证机制可以
  回调此接口的实现程序来确定是否应该允许此连接。如果回调内实现不恰当，默认
  接受所有域名，则有安全风险。
- 利用 X509TrustManager 子类中的 checkServerTrusted 函数效验服务器端证书的合法性。 说明： 在实现的 X509TrustManager 子类中未对服务端的证书做检验，这样会导致不被信 任的证书绕过证书效验机制
- META-INF 目录中不能包含如.apk,.odex,.so 等敏感文件，该文件夹没有经过签名，容易被恶意替换。
- Receiver/Provider 不能在毫无权限控制的情况下，将 android:export 设置为 true。对于内部使用的组件，显示设置组件的"android:exported"属性为 false
- 阻止 webview 通过 file:schema 方式访问本地敏感数据
- 不要广播敏感信息，只能在本应用使用 LocalBroadcast
- 不要把敏感信息打印到log中
- 应用发布前确保 android:debuggable 属性设置为 false
- 使用 Intent Scheme URL 需要做过滤。
  说明：
  如果浏览器支持 Intent Scheme Uri 语法，如果过滤不当，那么恶意用户可能通过浏
  览器 js 代码进行一些恶意行为，比如盗取 cookie 等。如果使用了 Intent.parseUri
  函 数 ， 获 取 的 intent 必 须 严格过滤， intent 至少包含
  addCategory(“android.intent.category.BROWSABLE”) ， setComponent(null) ，
  setSelector(null)3 个策略
- 密钥加密存储或者经过变形处理后用于加解密运算，切勿硬编码到代码中
- 将所需要动态加载的文件放置在 apk 内部，或应用私有目录中
- 使用 Android 的 AES/DES/DESede 加密算法时，不要使用默认的加密模式ECB，应显示指定使用 CBC 或 CFB 加密模式。
  说明：
  加密模式 ECB、CBC、CFB、OFB 等，其中 ECB 的安全性较弱，会使相同的铭文
  在不同的时候产生相同的密文，容易遇到字典攻击，建议使用 CBC 或 CFB 模式。
  ECB：Electronic codebook，电子密码本模式
  CBC：Cipher-block chaining，密码分组链接模式
  CFB：Cipher feedback，密文反馈模式
  OFB：Output feedback，输出反馈模式
- 不要使用 loopback 来通信敏感信息
- 对于不需要使用 File 协议的应用，禁用 File 协议，显式设置 webView.getSettings().setAllowFileAccess(false)，对于需要使用 File 协议的应用，禁止 File
  协议调用 JavaScript，显式设置 webView.getSettings().setJavaScriptEnabled(false)
- Android APP 在 HTTPS 通信中，验证策略需要改成严格模式。说明：Android APP 在 HTTPS 通信中，使用 ALLOW_ALL_HOSTNAME_VERIFIER，表示允许和
  所有的 HOST 建立 SSL 通信，这会存在中间人攻击的风险，最终导致敏感信息可能会被劫持，以及其他形式的攻击
- Android5.0 以后安全性要求 较高的应用 应该使 用 window.setFlag (LayoutParam.FLAG_SECURE) 禁止录屏
- zip 中不建议允许../../file 这样的路径，可能被篡改目录结构，造成攻击。
  说明：当 zip 压缩包中允许存在"../"的字符串，攻击者可以利用多个"../"在解压时改变 zip 文件存放的位置，当文件已经存在是就会进行覆盖，如果覆盖掉的文件是 so、dex 或者 odex 文件，就有可能造成严重的安全问题
- 开放的 activity/service/receiver 等需要对传入的 intent 做合法性校验
- 加密算法：使用不安全的 Hash 算法(MD5/SHA-1)加密信息，存在被破解的风险，建议使用 SHA-256 等安全性更高的 Hash 算法
- Android WebView 组件加载网页发生证书认证错误时,采用默认的处理方法handler.cancel()，停止加载问题页面。
  说明：
  Android WebView 组件加载网页发生证书认证错误时，会调用 WebViewClient 类的
  onReceivedSslError 方法，如果该方法实现调用了 handler.proceed()来忽略该证书
  错误，则会受到中间人攻击的威胁，可能导致隐私泄露
- 直接传递命令字或者间接处理有敏感信息或操作时，避免使用 socket 实现，使用能够控制权限校验身份的方式通讯

## 其他
- 不要通过 Msg 传递大的对象，会导致内存问题
- 不能使用 System.out.println 打印 log
- Log 的 tag 不能为空
